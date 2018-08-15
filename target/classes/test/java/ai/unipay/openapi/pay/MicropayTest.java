package ai.unipay.openapi.pay;

import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.pay.MicropayRequest;
import ai.unipay.openapi.response.pay.MicropayResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 刷卡支付示例
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-29
 */
public class MicropayTest {

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
     * 刷卡支付示例
     * 说明：
     * 收银员使用扫码设备读取用户刷卡授权码（二维码或条码信息传送至商户收银台），由商户收银台或者商户后台调用该接口发起支付
     * <p>
     * 支付流程介绍
     * 步骤1：微信：用户选择刷卡支付付款并打开微信，进入“我”->“钱包”->“收付款”条码界面；
     * 支付宝：用户登录支付宝钱包，点击首页“付款”，进入付款码界面；
     * 步骤2：商户收银员用扫码设备扫描用户的条码/二维码，商户系统调用刷卡支付接口提交支付；
     * 步骤3：付款成功后商家收银系统会拿到支付成功或者失败的结果。
     * <p>
     * 适用场景：
     * 1：便利店，药店、超市等实体店通过扫客户微信、支付宝等钱包的付款码进行支付
     * 2：场景化自动售货机、自动售票机、公交车、自动储物柜等通过固定式扫码枪引导客户主动被扫支付
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
     * 例如接口通信地址：/haipay/micropay
     * 在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response）
     * 如上接口名对应的类为：
     * MicropayRequest（请求类）
     * MicropayResponse（响应类）
     * <p>
     * ③.通过DefaultClient.execute()发起请求，通过响应类接收返回参；
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
    public void micropay() {

        MicropayRequest micropayRequest = new MicropayRequest();
        /**
         * appid
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        micropayRequest.setAppid(APPID);
        /**
         * 支付金额
         * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        micropayRequest.setTotalAmount(1);
        /**
         * 商户外部订单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        micropayRequest.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(14));
        /**
         * 用户付款码
         * 支付授权码，实际字符串长度以开发者获取的付款码长度为准
         * 付款码即微信、支付宝等钱包上的付款条码/二维码
         * 必填
         */
        micropayRequest.setAuthCode("281485633355630220");
        /**
         * 商户外部授权订单号
         * 商户平台生成的授权订单号，用于查询标识冻结
         * 非必填
         * authOrderNo和authConfirmMode都填写时为预授权支付
         */
//        micropayRequest.setAuthOrderNo("auth201807162033496660229010");
        /**
         * 授权支付模式
         * COMPLETE：转交易支付完成结束预授权;NOT_COMPLETE：转交易支付完成不结束预授权
         * 非必填
         * authOrderNo和authConfirmMode都填写时为预授权支付
         */
//        micropayRequest.setAuthConfirmMode("NOT_COMPLETE");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        micropayRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
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
        MicropayResponse micropayResponse = defaultClient.execute(micropayRequest);

        System.out.println("返回：" + JSON.toJSONString(micropayResponse));
    }

    /**
     * 运、渠接入
     */
    @Test
    public void orgMicropay() {

        MicropayRequest micropayRequest = new MicropayRequest();
        /**
         * appid
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        micropayRequest.setAppid(ORG_APPID);
        /**
         * 机构号
         * 机构号，用于识别渠道商，运营商
         * 必填
         */
        micropayRequest.setOrgId(ORG_ID);
        /**
         * 商户号
         * 商户号，系统生成的商户号，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
        micropayRequest.setMerchantNo("1573824026");
        /**
         * 外部商户号
         * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
//        micropayRequest.setOutMerchantNo("2018070917084157138136126434182");
        /**
         * 支付金额
         * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        micropayRequest.setTotalAmount(1);
        /**
         * 商户外部订单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        micropayRequest.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(14));
        /**
         * 用户付款码
         * 支付授权码，实际字符串长度以开发者获取的付款码长度为准
         * 付款码即微信、支付宝等钱包上的付款条码/二维码
         * 特殊必填（预授权支付时可不填）
         */
        micropayRequest.setAuthCode("288324190294324176");
        /**
         * 商户授权订单号
         * 商户平台生成的授权订单号，用于查询标识冻结
         * 非必填
         * authOrderNo和authConfirmMode都填写时为预授权支付
         */
//        micropayRequest.setAuthOrderNo("2018072010002001090261989435");
        /**
         * 授权支付模式
         * COMPLETE：转交易支付完成结束预授权;NOT_COMPLETE：转交易支付完成不结束预授权
         * 非必填
         * authOrderNo和authConfirmMode都填写时为预授权支付
         */
//        micropayRequest.setAuthConfirmMode("NOT_COMPLETE");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        micropayRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
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
        MicropayResponse micropayResponse = defaultClient.execute(micropayRequest);
        System.out.println("返回：" + JSON.toJSONString(micropayResponse));


    }
}
