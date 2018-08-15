package ai.unipay.openapi.request.merchant;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.merchant.MerchantApplyResponse;
import lombok.Data;

/**
 * 商户入驻申请
 *
 * @author zhenghang
 * @version 版本号：
 * @Date: 2018-07-02
 */
@Data
public class MerchantApplyRequest extends BaseRequest implements GenerateRequest<MerchantApplyResponse> {

    /**
     * 外部商户号
     * 用于识别商户
     * 必填
     */
    private String outMerchantNo;
    /**
     * 商户名称
     * 必填
     */
    private String merchantName;
    /**
     * 手机号码
     * 必填
     */
    private String mobilePhone;
    /**
     * 联系人名称
     * 必填
     */
    private String contactName;
    /**
     * 电子邮箱
     * 必填
     */
    private String email;
    /**
     * 详细地址
     * 必填
     */
    private String address;
    /**
     * 回调地址
     * 必填
     */
    private String returnUrl;

    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<MerchantApplyResponse> getResponseClass() {
        return MerchantApplyResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/merchant-apply";
    }
}
