package ai.unipay.openapi.request.invoice;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.invoice.InvoiceQrCodeResponse;
import lombok.Data;

@Data
public class InvoiceQrCodeRequest extends BaseRequest implements GenerateRequest<InvoiceQrCodeResponse> {

    /**
     * 开票金额
     * 开票金额，只能为人民币交易，接口中参数开票金额单位为【分】
     * 必填
     */
    private Integer amount;

    /**
     * 支付时间
     * 如果需要与支付进行绑定时，支付时间必填
     * 非必填
     */
    private String payTime;

    /**
     * 系统支付订单号
     * 如果需要与支付进行绑定时,系统支付订单号必填
     * 特殊非必须
     */
    private String orderNo;

    /**
     * 外部开票流水号
     * 外部开票流水号由商户自定义生成
     */
    private String outSerialNo;

    /**
     * 回调地址
     * 电子发票开票成功后服务端主动通知商户服务器里指定的页面http/https路径。
     * 具体返回参数详见文档
     * 必填
     */
    private String returnUrl;


    @Override
    public Class<InvoiceQrCodeResponse> getResponseClass() {
        return InvoiceQrCodeResponse.class;
    }

    @Override
    public String getServerUrl() {
        return "/haipay/invoice-qrcode";
    }
}
