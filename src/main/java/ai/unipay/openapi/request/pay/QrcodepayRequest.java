package ai.unipay.openapi.request.pay;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.pay.QrcodepayResponse;
import lombok.Data;

/**
 * 预下单
 * 通过预生成订单获取支付URL并生成二维码后即可扫码付款
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class QrcodepayRequest extends BaseRequest implements GenerateRequest<QrcodepayResponse> {

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
     * 优惠金额-暂未使用
     * 参与优惠计算的金额，仅支付宝订单有效，单位为【分】
     * 如果该值未传入，但传入了【订单总金额】和【不可打折金额】，则该值默认为【订单总金额】-【不可打折金额】
     * 非必填
     */
    private Integer discountableAmount;
    /**
     * 优惠券-暂未使用
     * 非必填
     */
    private String discountCoupon;
    /**
     * 商品名称
     * 该信息将透传至第三方支付公司系统，并在客户端明细中展示。
     * 非必填
     */
    private String body;
    /**
     * 商品详情
     * 说明详见文档->商品明细说明
     * 在订单中展示本次交易支付所使用的单品券优惠的商品优惠信息
     * 非必填
     */
    private String detail;
    /**
     * 附加字段
     * 商家数据包，原样返回
     * 非必填
     */
    private String attach;
    /**
     * 商户外部订单号
     * 建议商户外部订单号长度为30-32位
     * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     * 必填
     */
    private String outTradeNo;
    /**
     * 回调地址
     * 预下单用户支付成功后服务端主动通知商户服务器里指定的页面http/https路径。
     * 具体返回参数详见文档
     * 必填
     */
    private String returnUrl;
    /**
     * 系统中的openid
     * 用于区分订单归属那个门店，用户收款
     * 如果输入错误或者非本商户的openid，系统将自动过滤，订单将变成商户收款
     * 获取openid -> 商户平台 -> 用户管理 -> 用户列表 -> 详细信息
     * 非必填
     */
    private String openid;
    /**
     * 订单备注
     * 仅做记录
     * 非必填
     */
    private String note;

    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<QrcodepayResponse> getResponseClass() {
        return QrcodepayResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/qrcodepay";

    }
}
