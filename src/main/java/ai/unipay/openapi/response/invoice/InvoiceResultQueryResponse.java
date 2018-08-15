package ai.unipay.openapi.response.invoice;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InvoiceResultQueryResponse extends GenerateResponse {

    /**
     * 外部开票流水号
     * 由商户自定义生成
     */
    @JsonProperty("out_serial_no")
    private String outSerialNo;

    /**
     * 系统开票流水
     * 系统生成的开票流水号
     */
    @JsonProperty("serial_no")
    private String serialNo;

    /**
     * 发票代码
     */
    @JsonProperty("invoice_code")
    private String invoiceCode;

    /**
     * 发票号码
     */
    @JsonProperty("invoice_no")
    private String invoiceNo;

    /**
     * 开票时间
     */
    @JsonProperty("invoice_date")
    private String invoiceDate;

    /**
     * 发票版式文件
     * 发票版式文件是一个pdf写下载链接，通过链接下载电子发票
     */
    @JsonProperty("data_file")
    private String dataFile;

    /**
     * 发票开票状态
     */
    @JsonProperty("status")
    private String status;
}
