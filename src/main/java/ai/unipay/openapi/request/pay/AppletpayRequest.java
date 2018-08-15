package ai.unipay.openapi.request.pay;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.pay.AppletpayResponse;
import lombok.Data;

/**
 * 微信小程序支付
 * 用于获取小程序请求下单支付
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-29
 */
@Data
public class AppletpayRequest extends BaseRequest implements GenerateRequest<AppletpayResponse> {

    /**
     * 商户号
     * 商户号，系统生成的商户号，当传入机构号时，商户号和外部商户号特殊选填二选一，用于运营商、渠道商区分商户
     * 特殊非必填
     */
    private String merchantNo;
    /**
     * 外部商户号
     * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，当传入机构号时，商户号和外部商户号特殊选填二选一，用于运营商、渠道商区分商户
     * 特殊非必填
     */
    private String outMerchantNo;
    /**
     * 支付金额
     * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
     * 必填
     */
    private Integer totalAmount;
    /**
     * 商户外部订单号
     * 建议商户外部订单号长度为30-32位
     * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     * 必填
     */
    private String outTradeNo;
    /**
     * 回调地址
     * 异步接收支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
     * 必填
     */
    private String returnUrl;
    /**
     * 微信分配的小程序ID
     * 必填
     */
    private String subAppid;
    /**
     * 用户标识
     * 小程序可以通过微信官方提供的登录能力方便地获取用户身份标识
     * 必填
     */
    private String subOpenid;
    /**
     * 终端IP
     * APP和网页支付提交用户端ip
     * 必填
     */
    private String spbillCreateIp;

    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<AppletpayResponse> getResponseClass() {
        return AppletpayResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/applet-pay";
    }
}
