package ai.unipay.openapi.response.merchant;

import ai.unipay.openapi.response.GenerateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 商户入驻申请
 *
 * @author zhenghang
 * @version 版本号：
 * @Date: 2018-07-02
 */
@Data
public class MerchantApplyResponse extends GenerateResponse {
    /**
     * 商户号
     * 用于识别商户
     */
    @JsonProperty("merchant_no")
    private String merchantNo;
    /**
     * 入驻链接
     */
    @JsonProperty("entering_url")
    private String enteringUrl;
    /**
     * 外部商户号
     * 用于识别商户
     */
    @JsonProperty("out_merchant_no")
    private String outMerchantNo;
}
