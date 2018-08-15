package ai.unipay.openapi.response.pay;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 订单对账
 * 用于接收历史交易清单。
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class BillOrderResponse extends GenerateResponse {
    /**
     * 对账列表
     * 指定时间的历史交易清单
     */
    @JsonProperty("order_lists")
    private List<OrderBillList> orderLists;

}
