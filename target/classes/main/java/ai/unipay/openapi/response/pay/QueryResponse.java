package ai.unipay.openapi.response.pay;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询订单
 * 用于接收查询订单查询返回订单详情
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class QueryResponse extends GenerateResponse {

    /**
     * 订单交易状态
     * 订单的交易状态，详细见文档
     */
    @JsonProperty("trade_state")
    private String tradeState;
    /**
     * 系统交易订单号
     * 系统生成的支付订单号
     */
    @JsonProperty("trade_id")
    private String tradeId;
    /**
     * 支付时间
     * 订单支付的时间，格式为yyyy-MM-dd HH:mm:ss，如2009年12月25日9点10分10秒表示为2009-12-25 09:10:10
     */
    @JsonProperty("pay_time")
    private String payTime;
    /**
     * 支付金额
     * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
     */
    @JsonProperty("total_amount")
    private Integer totalAmount;
    /**
     * 退款金额
     * 退款金额单位为【分】
     */
    @JsonProperty("refund_amount")
    private Integer refundAmount;
    /**
     * 商户外部订单号
     * 商户支付的订单号由商户自定义生成，支付要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 优惠金额-暂未使用
     * 参与优惠计算的金额，仅支付宝订单有效，单位为【分】
     * 如果该值未传入，但传入了【订单总金额】和【不可打折金额】，则该值默认为【订单总金额】-【不可打折金额】
     */
    @JsonProperty("discountable_amount")
    private Integer discountableAmount;
    /**
     * 优惠券-暂未使用
     */
    @JsonProperty("discount_coupon")
    private String discountCoupon;
    /**
     * 商品名称
     * 该信息将透传至第三方支付公司系统，并在客户端明细中展示。
     */
    private String body;
    /**
     * 商品描述
     * 说明详见文档->商品明细说明
     * 在订单中展示本次交易支付所使用的单品券优惠的商品优惠信息
     */
    private String detail;
    /**
     * 支付方式
     * 0：微信
     * 1：支付宝
     */
    @JsonProperty("pay_type")
    private String payType;
    /**
     * 附加字段
     * 商家数据包，原样返回
     */
    private String attach;
    /**
     * 订单备注
     * 仅做记录
     */
    private String note;
}
