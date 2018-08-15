package ai.unipay.openapi.response.pay;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 预下单
 * 用于接收通过预下单获取支付url
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class QrcodepayResponse extends GenerateResponse {

    /**
     * 支付URL
     * 将URL生成二维码后即可扫码付款
     */
    @JsonProperty("qrcode_url")
    private String qrcodeUrl;

}
