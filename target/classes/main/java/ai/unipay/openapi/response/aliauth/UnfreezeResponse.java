package ai.unipay.openapi.response.aliauth;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 预授权解冻
 * 用于接收预授权解冻成功后返回的相关信息
 *
 * @author yuzhitao
 * @Date: 2018-07-16
 */
@Data
public class UnfreezeResponse  extends GenerateResponse {
    /**
     * 商户授权订单号
     * 商户平台生成的授权订单号，用于查询标识本次冻结
     */
    @JsonProperty("auth_order_no")
    private String authOrderNo;
    /**
     * 支付宝授权订单号
     * 支付宝生成的授权订单号，用于查询标识本次冻结
     */
    @JsonProperty("ali_auth_order_no")
    private String aliAuthOrderNo;
    /**
     * 商户授权操作流水号
     * 商户平台生成的操作流水号，用于查询标识本次解冻操作
     */
    @JsonProperty("operation_no")
    private String operationNo;
    /**
     * 支付宝授权操作流水号
     * 支付宝生成的操作流水号，用于查询标识本次解冻操作
     */
    @JsonProperty("ali_operation_no")
    private String aliOperationNo;
    /**
     * 解冻金额
     * 解冻金额，只能为人民币交易，接口中参数支付金额单位为【分】
     */
    @JsonProperty("unfreeze_amount")
    private String unfreezeAmount;
    /**
     * 资金解冻成功时间
     * 格式为yyyy-MM-dd HH:mm:ss，如2009年12月25日9点10分10秒表示为2009-12-25 09:10:10
     */
    @JsonProperty("ali_trans_time")
    private String aliTransTime;
    /**
     * 解冻状态
     * 解冻状态，详细见文档
     */
    @JsonProperty("status")
    private String status;
    /**
     * 外部商户授权单号
     * 建议商户外部订单号长度为30-32位
     * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     */
    @JsonProperty("out_auth_order_no")
    private String outAuthOrderNo;
}
