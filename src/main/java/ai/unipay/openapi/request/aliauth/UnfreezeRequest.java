package ai.unipay.openapi.request.aliauth;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.aliauth.UnfreezeResponse;
import lombok.Data;

/**
 * 预授权解冻
 * 根据用户支付宝中的“付款码/条形码”发起解冻
 *
 * @author yuzhitao
 * @Date: 2018-07-16
 */
@Data
public class UnfreezeRequest extends BaseRequest implements GenerateRequest<UnfreezeResponse> {
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
     * 外部商户授权单号
     * 建议商户外部订单号长度为30-32位
     * 商户支付的订单号由商户自定义生成，订单要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
     * 必填
     */
    private String outAuthOrderNo;
    /**
     * 商户授权订单号
     * 商户平台生成，由冻结时冻结接口返回
     * 必填
     */
    private String authOrderNo;
    /**
     * 解冻金额
     * 本次解冻需要解冻的金额
     * 必填
     */
    private Integer unfreezeAmount;

    @Override
    public Class<UnfreezeResponse> getResponseClass() {
        return UnfreezeResponse.class;
    }

    @Override
    public String getServerUrl() {
        return "/haipay/aliauth/unfreeze";
    }
}
