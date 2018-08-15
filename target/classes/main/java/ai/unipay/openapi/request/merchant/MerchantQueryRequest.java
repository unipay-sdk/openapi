package ai.unipay.openapi.request.merchant;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.merchant.MerchantQueryResponse;
import lombok.Data;

/**
 * 查询商户入驻详情
 *
 * @author zhenghang
 * @version 版本号：
 * @Date: 2018-07-03
 */
@Data
public class MerchantQueryRequest extends BaseRequest implements GenerateRequest<MerchantQueryResponse> {

    /**
     * 外部商户号
     * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，商户号和外部商户号特殊选填二选一
     * 特殊必填
     */
    private String outMerchantNo;
    /**
     * 商户号
     * 商户号，系统生成的商户号，用于识别商户，商户号和外部商户号特殊选填二选一
     * 特殊必填
     */
    private String merchantNo;

    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<MerchantQueryResponse> getResponseClass() {
        return MerchantQueryResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/merchant-query";
    }
}
