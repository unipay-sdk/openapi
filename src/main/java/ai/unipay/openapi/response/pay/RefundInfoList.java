package ai.unipay.openapi.response.pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 退款详情信息
 * 用于接收订单退款的单次退款详细信息
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class RefundInfoList {
    /**
     * 系统退款单号
     * 系统生成的退款订单号
     */
    @JsonProperty("trade_refund_id")
    private String tradeRefundId;
    /**
     * 商户退款单号
     * 商户系统内部的退款单号
     */
    @JsonProperty("out_refund_no")
    private String outRefundNo;
    /**
     * 退款时间
     * 订单退款时间的时间，格式为yyyy-MM-dd HH:mm:ss，如2009年12月25日9点10分10秒表示为2009-12-25 09:10:10
     */
    @JsonProperty("refund_time")
    private String refundTime;
    /**
     * 退款金额
     * 单位为分
     */
    @JsonProperty("refund_amount")
    private BigDecimal refundAmount;
    /**
     * 订单退款状态
     * 退款状态详细见文档
     */
    @JsonProperty("refund_status")
    private String refundStatus;
}
