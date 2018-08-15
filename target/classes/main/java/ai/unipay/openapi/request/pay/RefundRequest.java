package ai.unipay.openapi.request.pay;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.pay.RefundResponse;
import lombok.Data;

/**
 * 退款
 * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，
 * 支付款项将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class RefundRequest extends BaseRequest implements GenerateRequest<RefundResponse> {

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
     * 退款金额
     * 单位为分，可部分退款
     * 必填
     */
    private Integer refundFee;
    /**
     * 商户外部订单号
     * 商户支付的订单号由商户自定义生成
     * 特殊必填 系统交易订单号和商户外部订单号二选一 必须传其中一个
     */
    private String outTradeNo;
    /**
     * 系统交易订单号
     * 系统生成的支付订单号
     * 特殊必填 系统交易订单号和商户外部订单号二选一 必须传其中一个
     */
    private String tradeId;
    /**
     * 商户退款单号
     * 建议商户退款单号长度为30-32位
     * 商户系统内部的退款单号，要求商户系统内部唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     * 必填
     */
    private String outRefundNo;
    /**
     * 系统中的openid
     * 用于区分订单归属那个门店，用户收款
     * 如果输入错误或者非本商户的openid，系统将自动过滤，订单将变成商户收款
     * 获取openid -> 商户平台 -> 用户管理 -> 用户列表 -> 详细信息
     * 非必填
     */
    private String openid;

    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对 象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<RefundResponse> getResponseClass() {
        return RefundResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/refund";

    }
}
