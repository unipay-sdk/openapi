package ai.unipay.openapi.response.pay;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序支付
 * 用于接收小程序请求下单支付返回的调起支付的参数
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-29
 */
@Data
public class AppletpayResponse extends GenerateResponse {
    /**
     * 微信分配的小程序ID
     */
    private String appid;
    /**
     * 时间戳
     * 时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
     * 对应的小程序中调起支付的字段 timeStam
     */
    @JsonProperty("time_stamp")
    private Integer timeStamp;
    /**
     * 随机字符串
     * 对应的小程序中调起支付的字段 nonceStr
     */
    @JsonProperty("nonce_str")
    private String nonceStr;
    /**
     * 数据包
     * 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*
     * 对应的小程序中调起支付的字段 package
     */
    @JsonProperty("prepay_id")
    private Integer prepayId;
    /**
     * 签名
     * 对应的小程序中调起支付的字段 paySign
     */
    @JsonProperty("sign_type")
    private Integer signType;

}
