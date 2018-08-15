package ai.unipay.openapi.response.pay;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 退款详情
 * 用于接收指定订单的历史退款明细列表。
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class BillRefundResponse extends GenerateResponse {
    /**
     * 退款详情列表
     * 指定订单的退款清单列表
     */
    @JsonProperty("refund_lists")
    private List<RefundInfoList> refundLists;

}
