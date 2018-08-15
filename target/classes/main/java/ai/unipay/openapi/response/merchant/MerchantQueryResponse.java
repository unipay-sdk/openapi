package ai.unipay.openapi.response.merchant;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询商户入驻详情
 *
 * @author zhenghang
 * @version 版本号：
 * @Date: 2018-07-03
 */
@Data
public class MerchantQueryResponse extends GenerateResponse {


    /**
     * 外部商户号
     */
    @JsonProperty("out_merchant_no")
    private String outMerchantNo;
    /**
     * 商户号
     */
    @JsonProperty("merchant_no")
    private String merchantNo;
    /**
     * 入驻链接
     */
    @JsonProperty("entering_url")
    private String enteringUrl;
    /**
     * 状态：0：未受理，1：已受理，2：受理中
     */
    private String status;
}
