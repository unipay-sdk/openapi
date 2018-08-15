package ai.unipay.openapi.response.pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 对账单列表明细信息
 * <p>
 * Created by zhenghang on
 */
@Data
public class OrderBillList {

    /**
     * 系统中的openid
     * 用于区分订单归属那个门店，用户收款
     * 如果输入错误或者非本商户的openid，系统将自动过滤，订单将变成商户收款
     * 获取openid -> 商户平台 -> 用户管理 -> 用户列表 -> 详细信息
     */
    private String openid;
    /**
     * 商户外部订单号
     * 商户支付的订单号由商户自定义生成，支付要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
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
     * 订单交易状态
     * 订单的交易状态，详细见文档
     */
    @JsonProperty("trade_state")
    private String tradeState;
    /**
     * 支付金额
     * 交易金额，默认为人民币交易，接口中参数支付金额单位为【分】，参数值不能带小数
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;
    /**
     * 退款金额
     * 退款金额，默认为人民币交易，接口中参数支付金额单位为【分】，参数值不能带小数
     */
    @JsonProperty("refund_amount")
    private BigDecimal refundAmount;
    /**
     * 退款笔数
     * 当前返回退款笔数
     */
    @JsonProperty("refund_number")
    private Integer refundNumber;
    /**
     * 附加字段
     * 商家数据包，原样返回
     */
    private String attach;
    /**
     * 支付方式
     * 0：微信
     * 1：支付宝
     * 如果传系统将自动识别
     */
    @JsonProperty("pay_type")
    private String payType;
    /**
     * 订单备注
     * 仅做记录
     */
    private String note;


}
