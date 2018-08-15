package ai.unipay.openapi.response.invoice;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InvoiceQrCodeResponse extends GenerateResponse {

    /**
     * 系统开票流水
     * 系统生成的开票流水号
     */
    @JsonProperty("serial_no")
    private String serialNo;


    /**
     * 开票二维码链接
     * 将URL链接生成二维码后即可扫码进行开票
     */
    @JsonProperty("qrcode_url")
    private String qrcodeUrl;
}
