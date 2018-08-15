package ai.unipay.openapi.request.aliauth;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.aliauth.OperationQueryResponse;
import lombok.Data;

/**
 * 查询授权操作单
 * 通过流水单号查询授权操作单详情
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class OperationQueryRequest extends BaseRequest implements GenerateRequest<OperationQueryResponse> {
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
     * 特殊二选一 商户授权操作流水号和商户外部订单号 必传其一
     */
    private String outAuthOrderNo;
    /**
     * 商户授权操作流水号
     * 商户平台生成的操作流水号，用于查询标识本次冻结/解冻操作
     * 特殊二选一 商户授权操作流水号和商户外部订单号 必传其一
     */
    private String operationNo;
    /**
     * 查询类型
     * 0：冻结 1：解冻
     * 必填
     */
    private String operationType;

    @Override
    public Class<OperationQueryResponse> getResponseClass() {
        return OperationQueryResponse.class;
    }

    @Override
    public String getServerUrl() {
        return "/haipay/aliauth/query";
    }
}
