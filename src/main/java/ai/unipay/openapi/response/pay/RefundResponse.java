package ai.unipay.openapi.response.pay;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 退款
 * 用于接收退款成功后返回退款的信息
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class RefundResponse extends GenerateResponse {
    /**
     * 系统退款单号
     * 系统生成的退款订单号
     */
    @JsonProperty("haipay_refund_id")
    private String haipayRefundId;
    /**
     * 退款时间
     * 订单退款时间的时间，格式为yyyy-MM-dd HH:mm:ss，如2009年12月25日9点10分10秒表示为2009-12-25 09:10:10
     */
    @JsonProperty("refund_time")
    private String refundTime;

}
