package ai.unipay.openapi.pay;

import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.pay.QrcodepayRequest;
import ai.unipay.openapi.response.pay.QrcodepayResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 预下单示例
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-30
 */
public class QrcodepayTest {

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
    private String SERVERURL = "http://121.43.177.113:8084";

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
     * 预下单示例
     * 说明：
     * 商户系统先调用该接口在服务器后台生成预支付交易单，商户系统通过返回的支付url生成付款二维码；
     * <p>
     * 支付流程介绍：
     * 步骤1：商户系统调用预下单接口生成支付二维码
     * 步骤2：微信：用户通过消息或扫描二维码在微信内打开网页时，可以调用微信支付完成下单购买的流程；
     * 支付宝：用户支付宝钱包上的扫一扫功能扫商户二维码，完成付款；
     * 步骤3：支付完成后，支付相关结果通过异步回调通知发送给商户系统，商户系统需要接收处理，并返回应答；
     * <p>
     * 适用场景：
     * 1：便利店，药店、超市等实体店通过收银终端器展示支付二维码进行收款；
     * 2：场景化自动售货机、自动售票机、自动储物柜等通过终端展示支付二维码进行收款；
     * 3：电商等网上在线商城等通过展示支付二维码进行收款；
     * <p>
     * 接入流程：
     * 1：获取appid、appsecret；
     * 商户：
     * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
     * 运营商、渠道商（线下提供）：
     * 功能扩展 –> 开放应用 ->新增应用
     * <p>
     * 2：a.集成SDK根据文档说明快速接入实现快速开发；
     * ①. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API；
     * ②.确定接口对应的类；
     * 例如接口通信地址：/haipay/qrcodepay
     * 在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response）
     * 如上接口名对应的类为：
     * QrcodepayRequest（请求类）
     * QrcodepayResponse（响应类）
     * <p>
     * b.根据接口文档文档接入；
     * ①.根据文档初始化发起请求的相关参数；
     * ②.根据发起请求的相关参数进行签名（详见文档签名）；
     * ③.发起请求；
     * ④.根据文档对应返回值并进行相关逻辑处理；
     */

    /**
     * 商户接入
     */
    @Test
    public void qrcodepay() {

        QrcodepayRequest qrcodepayRequest = new QrcodepayRequest();
        /**
         * appid
         * 获取appid、appsecret ：
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        qrcodepayRequest.setAppid(APPID);
        /**
         * 支付金额
         * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        qrcodepayRequest.setTotalAmount(1);
        /**
         * 商户外部订单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        qrcodepayRequest.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(7));
        /**
         * 回调地址
         * 预下单用户支付成功后服务端主动通知商户服务器里指定的页面http/https路径。
         * 具体返回参数详见文档
         * 必填
         */
        qrcodepayRequest.setReturnUrl("http://www.rabit.top/main/test");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        qrcodepayRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 初始化链接和appSecret
         *
         * @param SERVERURL 域名链接
         * @param APPSECRET appSecret
         */
        DefaultClient defaultClient = new DefaultClient(SERVERURL, APPSECRET);
        /**
         * 初始化信息并发起请求
         *
         * @param microPayRequest 请求信息
         * @return 支付结果
         */
        QrcodepayResponse qrcodepayResponse = defaultClient.execute(qrcodepayRequest);

        System.out.println("返回：" + JSON.toJSONString(qrcodepayResponse));
    }

    /**
     * 运、渠接入
     */
    @Test
    public void orgQrcodepay() {

        QrcodepayRequest qrcodepayRequest = new QrcodepayRequest();
        /**
         * appid
         * 获取appid、appsecret ：
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        qrcodepayRequest.setAppid(ORG_APPID);
        /**
         * 机构号
         * 机构号，用于识别渠道商，运营商
         * 必填
         */
        qrcodepayRequest.setOrgId(ORG_ID);
        /**
         * 商户号
         * 商户号，系统生成的商户号，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
        qrcodepayRequest.setMerchantNo("1573824026");
        /**
         * 外部商户号
         * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
        qrcodepayRequest.setOutMerchantNo("");
        /**
         * 支付金额
         * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        qrcodepayRequest.setTotalAmount(1);
        /**
         * 商户外部订单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        qrcodepayRequest.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date()) + RandomDigital.randomOnlyNumber(7));
        /**
         * 回调地址
         * 预下单用户支付成功后服务端主动通知商户服务器里指定的页面http/https路径。
         * 具体返回参数详见文档
         * 必填
         */
        qrcodepayRequest.setReturnUrl("http://localhost:8084");
        /**
         * 系统中的openid
         * 用于区分订单归属那个门店，用户收款
         * 如果输入错误或者非本商户的openid，系统将自动过滤，订单将变成商户收款
         * 获取openid -> 商户平台 -> 用户管理 -> 用户列表 -> 详细信息
         * 非必填
         */
        qrcodepayRequest.setOpenid("11");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        qrcodepayRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
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
        QrcodepayResponse qrcodepayResponse = defaultClient.execute(qrcodepayRequest);

        System.out.println("返回：" + JSON.toJSONString(qrcodepayResponse));
    }
}
