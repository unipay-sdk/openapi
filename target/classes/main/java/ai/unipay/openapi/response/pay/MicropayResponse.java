package ai.unipay.openapi.response.pay;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 刷卡支付
 * 用于接收刷卡支付成功后返回的相关信息
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class MicropayResponse extends GenerateResponse {
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

}
