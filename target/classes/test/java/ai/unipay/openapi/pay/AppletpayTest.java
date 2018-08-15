package ai.unipay.openapi.pay;

import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.pay.AppletpayRequest;
import ai.unipay.openapi.response.pay.AppletpayResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 小程序支付示例
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-30
 */
public class AppletpayTest {
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
     * 微信小程序支付示例
     * 说明：
     * 商户系统通过该接口获取小程序调起支付相关参数
     * <p>
     * 支付流程介绍
     * 步骤1：通过该接口获取小程序调起支付相关参数；
     * 步骤2：根据接口返回值调起支付，
     * 小程序中package  = 接口返回的 prepay_id，
     * 小程序中signType = MD5，
     * 小程序中paySign = 接口返回的 sign_type，
     * 小程序中timeStamp = 接口返回的 time_stamp
     * 小程序中nonceStr = 接口返回的 nonce_str ；
     * 步骤3：支付完成后，支付相关结果通过异步回调通知发送给商户系统，商户系统需要接收处理，并返回应答；
     * <p>
     * 适用场景：
     * 1：微信小程序支付
     * <p>
     * 接入流程
     * 1：获取appid、appsecret
     * 商户：
     * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
     * 运营商、渠道商（线下提供）：
     * 功能扩展 –> 开放应用 ->新增应用
     * <p>
     * 2：a.集成SDK根据接口文档快速接入实现快速开发
     * ①. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API；
     * ②.确定接口对应的类；
     * 例如接口通信地址：/haipay/applet-pay
     * 在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response）
     * 如上接口名对应的类为：
     * appletpayRequest（请求类）
     * AppletpayResponse（响应类）
     * <p>
     * b.根据接口文档文档接入
     * ①.根据文档初始化发起请求的相关参数
     * ②.根据发起请求的相关参数进行签名（详见文档签名）
     * ③.发起请求
     * ④.根据文档对应返回值并进行相关逻辑处理
     */

    /**
     * 商户接入
     */
    @Test
    public void appletPay() {

        AppletpayRequest appletpayRequest = new AppletpayRequest();
        /**
         * appid
         * 获取appid、appsecret ：
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        appletpayRequest.setAppid(APPID);
        /**
         * 支付金额
         * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        appletpayRequest.setTotalAmount(1);
        /**
         * 回调地址
         * 异步接收支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
         * 必填
         */
        appletpayRequest.setReturnUrl("www.baidu.com");
        /**
         * 微信分配的小程序ID
         * 必填
         */
        appletpayRequest.setSubAppid("EE");
        /**
         * 终端IP
         * APP和网页支付提交用户端ip
         * 必填
         */
        appletpayRequest.setSpbillCreateIp("192.168.0.1");
        /**
         * 用户标识
         * 小程序可以通过微信官方提供的登录能力方便地获取用户身份标识
         * 必填
         */
        appletpayRequest.setSubOpenid("oepLq0BvozHlx6nn_30p6FryTq6M");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        appletpayRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 商户外部订单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        appletpayRequest.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(7));
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
        AppletpayResponse appletpayResponse = defaultClient.execute(appletpayRequest);
        System.out.println("返回：" + JSON.toJSONString(appletpayResponse));
    }

    /**
     * 运、渠接入
     */
    @Test
    public void orgAppletPay() {

        AppletpayRequest appletpayRequest = new AppletpayRequest();
        /**
         * appid
         * 获取appid、appsecret ：
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        appletpayRequest.setAppid(ORG_APPID);
        /**
         * 机构号
         * 机构号，用于识别渠道商，运营商
         * 必填
         */
        appletpayRequest.setOrgId(ORG_ID);
        /**
         * 商户号
         * 商户号，系统生成的商户号，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
        appletpayRequest.setMerchantNo("1573824026");
        /**
         * 外部商户号
         * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
//        appletpayRequest.setOutMerchantNo("");

        /**
         * 支付金额
         * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        appletpayRequest.setTotalAmount(1);
        /**
         * 回调地址
         * 异步接收支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
         * 必填
         */
        appletpayRequest.setReturnUrl("www.baidu.com");
        /**
         * 微信分配的小程序ID
         * 必填
         */
        appletpayRequest.setSubAppid("EE");
        /**
         * 终端IP
         * APP和网页支付提交用户端ip
         * 必填
         */
        appletpayRequest.setSpbillCreateIp("192.168.0.1");
        /**
         * 用户标识
         * 小程序可以通过微信官方提供的登录能力方便地获取用户身份标识
         * 必填
         */
        appletpayRequest.setSubOpenid("oepLq0BvozHlx6nn_30p6FryTq6M");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        appletpayRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 商户外部订单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        appletpayRequest.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(7));
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
        AppletpayResponse appletpayResponse = defaultClient.execute(appletpayRequest);
        System.out.println("返回：" + JSON.toJSONString(appletpayResponse));
    }
}
