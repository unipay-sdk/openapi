package ai.unipay.openapi.merchant;

import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.merchant.MerchantQueryRequest;
import ai.unipay.openapi.response.merchant.MerchantQueryResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 查询商户入驻详情
 *
 * @author zhenghang
 * @version 版本号：
 * @Date: 2018-07-04
 */
public class MerchantQueryTest {
    /**
     * 初始化SERVERURL
     */
    private String SERVERURL = "http://localhost:8084";

    /**
     * 初始化运、渠APPID
     */
    private String ORG_APPID = "cj59064f53-1693-48";
    /**
     * 初始化运、渠APPSECRET
     */
    private String ORG_APPSECRET = "66d28621-ec66-48ac-b50e-a5cc22e4ed1f";
    /**
     * 初始化机构号
     */
    private String ORG_ID = "15738240261573824026";

    /**
     * 查询商户入驻详情
     * <p>
     * 接入流程
     * 1：获取appid、appsecret
     * 运营商、渠道商（线下提供）：
     * 功能扩展 –> 开放应用 ->新增应用
     * <p>
     * 2：a.集成SDK根据接口文档快速接入实现快速开发
     * ①. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API；
     * ②.确定接口对应的类；
     * 例如接口通信地址：/haipay/merchant-query
     * 在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response）
     * 如上接口名对应的类为：
     * MerchantQueryRequest（请求类）
     * MerchantQueryResponse（响应类）
     * <p>
     * b.根据接口文档文档接入
     * ①.根据文档初始化发起请求的相关参数
     * ②.根据发起请求的相关参数进行签名（详见文档签名）
     * ③.发起请求
     * ④.根据文档对应返回值并进行相关逻辑处理
     */

    @Test
    public void merchantInfo() {
        MerchantQueryRequest merchantQueryRequest = new MerchantQueryRequest();
        /**
         * appid
         * 获取appid、appsecret ：
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        merchantQueryRequest.setAppid(ORG_APPID);
        /**
         * 机构号
         * 机构号，用于识别渠道商，运营商
         * 必填
         */
        merchantQueryRequest.setOrgId(ORG_ID);
        /**
         * 商户号
         * 商户号，系统生成的商户号，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
//        merchantQueryRequest.setMerchantNo("1573824026");
        /**
         * 外部商户号
         * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
        merchantQueryRequest.setOutMerchantNo("2018080219011133327221210362075");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        merchantQueryRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 初始化链接和appSecret
         *
         * @param SERVERURL 域名链接
         * @param APPSECRET appSecret
         */
        DefaultClient defaultClient = new DefaultClient(SERVERURL, ORG_APPSECRET);

        /**
         * 初始化信息并发起请求
         *
         * @param microPayRequest 请求信息
         * @return 支付结果
         */
        MerchantQueryResponse response = defaultClient.execute(merchantQueryRequest);
        System.out.println("返回：" + JSON.toJSONString(response));

    }


}
