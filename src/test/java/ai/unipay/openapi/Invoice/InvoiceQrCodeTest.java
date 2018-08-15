package ai.unipay.openapi.Invoice;


import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.invoice.InvoiceQrCodeRequest;
import ai.unipay.openapi.response.invoice.InvoiceQrCodeResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceQrCodeTest {

    /**
     * 初始化APPID
     */
    private String APPID = "hf14970713815RIA";
    /**
     * 初始化APPSECRET
     */
    private String APPSECRET = "0DIrHp3TWCLqT2fDzQ4f4zOl5Xnys6tA";

    /**
     * 初始化SERVERURL
     */
    private String SERVERURL = "http://118.31.65.169:8084";

    /**
     * 查询开票二维码
     */
    @Test
    public void invoiceQrCode() {
        InvoiceQrCodeRequest request = new InvoiceQrCodeRequest();

        /**
         * appid
         * 获取appid、appsecret ：
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 必填
         */
        request.setAppid(APPID);
        /**
         * 支付金额
         * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
         * 必填
         */
        request.setAmount(100);
        /**
         * 商户外部订单号
         * 建议商户外部订单号长度为30-32位
         * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
         * 必填
         */
        request.setOutSerialNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomDigital.randomOnlyNumber(30));
        /**
         * 回调地址
         * 预下单用户支付成功后服务端主动通知商户服务器里指定的页面http/https路径。
         * 具体返回参数详见文档
         * 必填
         */
        request.setReturnUrl("http://118.31.65.169:10085");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        request.setNonceStr(RandomDigital.randomOnlyNumber(32));
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
        InvoiceQrCodeResponse response = defaultClient.execute(request);
        System.out.println("返回：" + JSON.toJSONString(response));
    }
}
