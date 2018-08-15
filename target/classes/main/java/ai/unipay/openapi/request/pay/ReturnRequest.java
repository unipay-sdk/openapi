package ai.unipay.openapi.request.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 提供用户用于接收通知参数
 * 预下单用户支付成功后服务端返回通知对象
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-29
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnRequest {
    /**
     * 状态码
     * 业务返回码
     */
    private String code;
    /**
     * 订单交易状态
     * 订单的交易状态，详细见文档
     */
    private String trade_state;
    /**
     * 商户外部订单号
     * 商户支付的订单号由商户自定义生成，支付要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     */
    private String out_trade_no;
    /**
     * 系统交易订单号
     * 系统生成的支付订单号
     */
    private String trade_id;
    /**
     * 错误提示
     * 业务返异常的描述
     */
    private String err_msg;
    /**
     * 支付时间
     * 订单支付的时间，格式为yyyy-MM-dd HH:mm:ss，如2009年12月25日9点10分10秒表示为2009-12-25 09:10:10
     */
    private String pay_time;
    /**
     * 附加字段
     * 商家数据包，原样返回
     */
    private String attach;
    /**
     * 支付方式
     * 0：微信
     * 1：支付宝
     */
    private String pay_type;

}
