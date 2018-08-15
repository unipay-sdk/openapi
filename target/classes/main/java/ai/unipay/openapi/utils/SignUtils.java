package ai.unipay.openapi.utils;

import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.GenerateResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 请求参数签名
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-29
 */
@Slf4j
public class SignUtils {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 参数排序
     *
     * @param generateRequest 请求参数
     * @param <T>             实际返回Response对象
     * @return 排序后的map集合
     * @throws Exception 异常
     */
    public static <T extends GenerateResponse> Map<String, String> getRequestMap(GenerateRequest<?> generateRequest) throws Exception {
        int index = 0;
        Map<String, String> map = new TreeMap<>();
        Class<?> clazz = generateRequest.getClass();
        while (clazz != null) {
            declaredFieldsToMap(map, generateRequest, clazz);
            clazz = clazz.getSuperclass();
            //控制随意将对象嵌入导致死循环，最多迭代三次
            if (index >= 3) {
                break;
            }
            index++;
        }
        return map;
    }

    /**
     * 根据请求将请求参数转换为map
     *
     * @param map             容器
     * @param generateRequest 需要转换的对象
     * @param clazz           需要转换的对象类
     */
    private static void declaredFieldsToMap(Map<String, String> map, GenerateRequest<?> generateRequest, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(generateRequest);
                //将sign字段忽略，不加入参数中
                if (value != null && !field.getName().equals("sign")) {
                    if (value instanceof String) {
                        map.put(humpToLine(field.getName()), (String) value);
                    } else if (value instanceof Integer) {
                        map.put(humpToLine(field.getName()), String.valueOf(value));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成签名并返回请求参数MAP
     * 用于返回签名后的请求参数MAP
     *
     * @param generateRequest 请求参数
     * @param appSecret       appSecret
     * @param <T>             实际返回Response对象
     * @return 请求参数MAP
     * @throws Exception
     */
    public static <T extends GenerateResponse> Map<String, String> getSign(GenerateRequest<?> generateRequest, String appSecret) throws Exception {
        //对象转成Map
        Map<String, String> applicationMap = getRequestMap(generateRequest);
        //排序拼接
        String sign = sign(applicationMap, appSecret);
        applicationMap.put("sign", sign);
        return applicationMap;
    }

    /**
     * 拼接排序并签名
     * 用于校验返回值的签名是否正确
     *
     * @param map       请求参数
     * @param appSecret appSecret
     * @return 签名
     * @throws Exception
     */
    public static String getResponseSign(Map<String, String> map, String appSecret) throws Exception {
        List<Map.Entry<String, String>> infos = getSequence(map);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> item : infos) {
            if (item.getKey() != null && !"open_sign".equals(item.getKey())) {
                stringBuilder.append(item.getKey());
                stringBuilder.append("=");
                Object value = item.getValue();
                if (value instanceof String) {
                    stringBuilder.append((String) value);
                    stringBuilder.append("&");
                } else if (value instanceof Integer) {
                    stringBuilder.append(String.valueOf(value));
                    stringBuilder.append("&");
                } else if (value instanceof List) {
                    List<Map<String, String>> orderList = new ArrayList<Map<String, String>>();
                    for (int i = 0; i < ((List) value).size(); i++) {
                        Map maps = JSON.parseObject(String.valueOf(((List) value).get(i)), Map.class);
                        List<Map.Entry<String, String>> sequence = getSequence(maps);
                        Map<String, String> listInfo = new TreeMap<>();
                        //解析mapList为map
                        for (Map.Entry<String, String> info : sequence) {
                            listInfo.put(info.getKey(), info.getValue());
                        }
                        orderList.add(listInfo);
                    }
                    stringBuilder.append(JSON.toJSONString(orderList));
                    stringBuilder.append("&");
                }
            }
        }
        stringBuilder.append("appsecret=");
        stringBuilder.append(appSecret);
        String sign = DigestUtils.md5Hex(stringBuilder.toString()).toUpperCase();
        return sign;
    }

    /**
     * 根据ASCII 码从小到大排序（字典序）
     *
     * @param map 需排序的字典集map
     * @return 排序后的map
     */
    private static List<Map.Entry<String, String>> getSequence(Map<String, String> map) {
        List<Map.Entry<String, String>> infos = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        infos.sort(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });
        return infos;
    }


    /**
     * 拼接签名
     * 用于获取签名
     *
     * @param params    参数
     * @param appSecret appSecret
     * @return 签名
     */
    public static String sign(Map<String, String> params, String appSecret) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue());
                stringBuilder.append("&");
            }
            stringBuilder.append("appsecret=");
            stringBuilder.append(appSecret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sign = DigestUtils.md5Hex(stringBuilder.toString()).toUpperCase();
        return sign;
    }

    /**
     * 驼峰命名转下划线命名
     *
     * @param str 字段名
     * @return
     */
    private static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
