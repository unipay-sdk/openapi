package ai.unipay.openapi.request.invoice;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.invoice.InvoiceResultQueryResponse;
import lombok.Data;

@Data
public class InvoiceResultQueryRequest extends BaseRequest implements GenerateRequest<InvoiceResultQueryResponse> {

    /**
     * 外部开票流水号
     * 外部开票流水号由商户自定义生成
     */
    private String outSerialNo;

    /**
     * 系统开票流水
     * 系统生成的开票流水号
     */
    private String serialNo;

    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<InvoiceResultQueryResponse> getResponseClass() {
        return InvoiceResultQueryResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/invoice-result";
    }

}
