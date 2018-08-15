package ai.unipay.openapi;

import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.GenerateResponse;
import ai.unipay.openapi.utils.HttpClientUtils;
import ai.unipay.openapi.utils.SignUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * 请求相关处理
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-29
 */
@Slf4j
public class DefaultClient implements Client {

    /**
     * appSecret
     */
    private String appSecret;
    /**
     * 跳转链接
     */
    private String serverUrl;

    /**
     * 初始化链接和appSecret
     *
     * @param serverUrl 域名链接
     * @param appSecret appSecret
     */
    public DefaultClient(String serverUrl, String appSecret) {
        super();
        this.serverUrl = serverUrl;
        this.appSecret = appSecret;
    }

    /**
     * 初始化信息并发起请求
     *
     * @param generateRequest 请求信息
     * @param <T>             返回对象
     * @return 结果
     */
    @Override
    public <T extends GenerateResponse> T execute(GenerateRequest<T> generateRequest) {
        try {
            if (Objects.isNull(this.serverUrl)) {
                GenerateResponse generateResponse = new GenerateResponse();
                generateResponse.setCode("300");
                generateResponse.setErrMsg("域名不能为空");
                return (T) JSON.parseObject(JSON.toJSONBytes(generateResponse), generateRequest.getResponseClass());
            }
            this.serverUrl = this.serverUrl + generateRequest.getServerUrl();
            return this.doPost(generateRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发起请求
     *
     * @param generateRequest 请求参数
     * @param <T>             返回对象
     * @return 请求返回值
     * @throws Exception 异常
     */
    private <T extends GenerateResponse> T doPost(GenerateRequest<T> generateRequest) throws Exception {
        //签名
        Map<String, String> applicationParams = SignUtils.getSign(generateRequest, appSecret);
        //初始化请求参数
        String urlParams = HttpClientUtils.getUrlParamsByMap(applicationParams);
        log.info("request：{}，server url：{}", urlParams, serverUrl);
        HttpClientUtils.RequestResult requestResult = HttpClientUtils.post(serverUrl, urlParams);
        log.info("response：{}，server url：{}", JSON.toJSONString(requestResult), serverUrl);
        //参数转换
        if (requestResult.result) {
            //进行签名验证，然后转为对象返回
            Map<String, String> map = JSON.parseObject(requestResult.content, Map.class);
            String openSign = map.get("open_sign");
            //sign不为null的时候进行签名，主要由于低版本未进行签名返回，如判断为空可能导致失败
            if (openSign != null) {
                //对返回参数进行签名
                String sign = SignUtils.getResponseSign(map, appSecret);
                log.info("请求签名：{}，返回参数签名l：{}", openSign, sign);
                if (Objects.isNull(openSign) && !openSign.equals(sign)) {
                    GenerateResponse generateResponse = new GenerateResponse();
                    generateResponse.setCode("201");
                    generateResponse.setErrMsg("返回参数验签失败");
                    return (T) JSON.parseObject(JSON.toJSONBytes(generateResponse), generateRequest.getResponseClass());
                }
            }
            GenerateResponse response = JSON.parseObject(requestResult.content, generateRequest.getResponseClass());
            return (T) response;
        }
        //请求异常
        GenerateResponse generateResponse = new GenerateResponse();
        generateResponse.setCode("301");
        generateResponse.setErrMsg("".equals(requestResult.content) ? "系统错误" : requestResult.content);
        return (T) JSON.parseObject(JSON.toJSONBytes(generateResponse), generateRequest.getResponseClass());
    }

}
