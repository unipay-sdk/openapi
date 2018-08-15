package ai.unipay.openapi.merchant;

import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.merchant.MerchantApplyRequest;
import ai.unipay.openapi.response.merchant.MerchantApplyResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 商户入驻
 *
 * @author zhenghang
 * @version 版本号：
 * @Date: 2018-07-05
 */
public class MerchantApplyTest {
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
     * 商户入驻申请
     * <p>
     * 接入流程
     * 1：获取appid、appsecret
     * 运营商、渠道商（线下提供）：
     * 功能扩展 –> 开放应用 ->新增应用
     * <p>
     * 2：a.集成SDK根据接口文档快速接入实现快速开发
     * ①. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API；
     * ②.确定接口对应的类；
     * 例如接口通信地址：/haipay/merchant-apply
     * 在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response）
     * 如上接口名对应的类为：
     * MerchantApplyRequest（请求类）
     * MerchantApplyResponse（响应类）
     * <p>
     * b.根据接口文档文档接入
     * ①.根据文档初始化发起请求的相关参数
     * ②.根据发起请求的相关参数进行签名（详见文档签名）
     * ③.发起请求
     * ④.根据文档对应返回值并进行相关逻辑处理
     */

    @Test
    public void orgCreateMerchant() {
        MerchantApplyRequest merchantApplyRequest = new MerchantApplyRequest();

        /**
         * appid
         * 获取appid、appsecret ：
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        merchantApplyRequest.setAppid(ORG_APPID);
        /**
         * 机构号
         * 机构号，用于识别渠道商，运营商
         * 必填
         */
        merchantApplyRequest.setOrgId(ORG_ID);
        /**
         * 外部商户号
         * 用于识别商户的唯一标识，由运营商、机构商自定义生成，需保持唯一性
         * 必填
         */
        merchantApplyRequest.setOutMerchantNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(14));
        /**
         * 商户名称
         * 必填
         */
        merchantApplyRequest.setMerchantName("test-merchant-y");
        /**
         * 手机号码
         * 必填
         */
        merchantApplyRequest.setMobilePhone("15868191072");
        /**
         * 联系人名称
         * 必填
         */
        merchantApplyRequest.setContactName("test");
        /**
         * 电子邮箱
         * 必填
         */
        merchantApplyRequest.setEmail("555555555@qq.com");
        /**
         * 详细地址
         * 必填
         */
        merchantApplyRequest.setAddress("浦三路XX号");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        merchantApplyRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 回调地址
         */
        merchantApplyRequest.setReturnUrl("tst");
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
        MerchantApplyResponse merchantApplyResponse = defaultClient.execute(merchantApplyRequest);
        System.out.println("返回：" + JSON.toJSONString(merchantApplyResponse));

    }

}
