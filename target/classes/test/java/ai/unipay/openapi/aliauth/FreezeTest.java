package ai.unipay.openapi.aliauth;

import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.aliauth.FreezeRequest;
import ai.unipay.openapi.response.aliauth.FreezeResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 预授权冻结示例
 *
 * @author yuzhitao
 * @version 版本号：V1.0
 * @Date: 2018-07-16
 */
public class FreezeTest {

    /**
     * 初始化APPID
     */
    private String APPID = "hf1020354855HWMA";
    /**
     * 初始化APPSECRET
     */
    private String APPSECRET = "dgyFFJJfVhXdQo9lNXxbvrNJs500AjKA";
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
     * 商户接入
     */
    @Test
    public void freeze() {

        FreezeRequest freezeRequest = new FreezeRequest();
        /**
         * appid
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        freezeRequest.setAppid(APPID);
        /**
         * 冻结金额
         * 冻结金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        freezeRequest.setFreezeAmount(3);
        /**
         * 外部商户授权单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        freezeRequest.setOutAuthOrderNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(14));
        /**
         * 用户付款码
         * 支付授权码，实际字符串长度以开发者获取的付款码长度为准
         * 付款码即微信、支付宝等钱包上的付款条码/二维码
         * 必填
         */
        freezeRequest.setAuthCode("283328235298824580");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        freezeRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 初始化链接和appSecret
         *
         * @param serverUrl 域名链接
         * @param appSecret appSecret
         */
        DefaultClient defaultClient = new DefaultClient(SERVERURL, APPSECRET);
        /**
         * 初始化信息并发起请求
         *
         * @param microPayRequest 请求信息
         * @return 支付结果
         */
        FreezeResponse freezeResponse = defaultClient.execute(freezeRequest);
        System.out.println("返回：" + JSON.toJSONString(freezeResponse));
    }

    /**
     * 运、渠接入
     */
    @Test
    public void orgFreeze() {

        FreezeRequest freezeRequest = new FreezeRequest();
        /**
         * appid
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        freezeRequest.setAppid(ORG_APPID);
        /**
         * 机构号
         * 机构号，用于识别渠道商，运营商
         * 必填
         */
        freezeRequest.setOrgId(ORG_ID);
        /**
         * 商户号
         * 商户号，系统生成的商户号，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
        freezeRequest.setMerchantNo("1573824026");
        /**
         * 外部商户号
         * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
//        freezeRequest.setOutMerchantNo("2018070917084157138136126434182");
        /**
         * 冻结金额
         * 冻结金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        freezeRequest.setFreezeAmount(5);
        /**
         * 外部商户授权单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        freezeRequest.setOutAuthOrderNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(14));
        /**
         * 用户付款码
         * 支付授权码，实际字符串长度以开发者获取的付款码长度为准
         * 付款码即微信、支付宝等钱包上的付款条码/二维码
         * 必填
         */
        freezeRequest.setAuthCode("280638313261511438");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        freezeRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 初始化链接和appSecret
         *
         * @param serverUrl 域名链接
         * @param appSecret appSecret
         */
        DefaultClient defaultClient = new DefaultClient(SERVERURL, ORG_APPSECRET);
        /**
         * 初始化信息并发起请求
         *
         * @param microPayRequest 请求信息
         * @return 支付结果
         */
        FreezeResponse freezeResponse = defaultClient.execute(freezeRequest);
        System.out.println("返回：" + JSON.toJSONString(freezeResponse));


    }
}
