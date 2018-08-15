package ai.unipay.openapi.request.aliauth;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.aliauth.FreezeResponse;
import lombok.Data;

/**
 * 预授权冻结
 * 根据用户支付宝中的“付款码/条形码”发起冻结
 *
 * @author yuzhitao
 * @Date: 2018-07-16
 */
@Data
public class FreezeRequest extends BaseRequest implements GenerateRequest<FreezeResponse> {
    /**
     * 商户号
     * 商户号，系统生成的商户号，当传入机构号时，商户号和外部商户号特殊选填二选一，用于运营商、渠道商区分商户
     * 特殊非必填
     */
    private String merchantNo;
    /**
     * 外部商户号
     * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，当传入机构号时，商户号和外部商户号特殊选填二选一，用于运营商、渠道商区分商户
     * 特殊非必填
     */
    private String outMerchantNo;
    /**
     * 系统中的openid
     * 用于区分订单归属那个门店，用户收款
     * 如果输入错误或者非本商户的openid，系统将自动过滤，订单将变成商户收款
     * 获取openid -> 商户平台 -> 用户管理 -> 用户列表 -> 详细信息
     * 非必填
     */
    private String openid;
    /**
     * 冻结金额
     * 交易金额，只能为人民币交易，接口中参数支付金额单位为【分】
     * 必填
     */
    private Integer freezeAmount;
    /**
     * 外部商户授权单号
     * 建议商户外部订单号长度为30-32位
     * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     * 必填
     */
    private String outAuthOrderNo;
    /**
     * 用户付款码
     * 支付授权码，实际字符串长度以开发者获取的付款码长度为准
     * 付款码即支付宝上的付款条码/二维码
     * 必填
     */
    private String authCode;

    @Override
    public Class<FreezeResponse> getResponseClass() {
        return FreezeResponse.class;
    }

    @Override
    public String getServerUrl() {
        return "/haipay/aliauth/freeze";
    }
}
