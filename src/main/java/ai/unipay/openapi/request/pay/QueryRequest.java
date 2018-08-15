package ai.unipay.openapi.request.pay;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.pay.QueryResponse;
import lombok.Data;

/**
 * 查询订单
 * 通过订单号查询订单详情
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class QueryRequest extends BaseRequest implements GenerateRequest<QueryResponse> {

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
     * 商户外部订单号
     * 商户支付的订单号由商户自定义生成
     * 特殊必填 系统交易订单号和商户外部订单号二选一 必须传其中一个
     */
    private String outTradeNo;
    /**
     * 系统交易订单号
     * 系统生成的支付订单号
     * 特殊必填 系统交易订单号和商户外部订单号二选一 必须传其中一个
     */
    private String tradeId;

    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<QueryResponse> getResponseClass() {
        return QueryResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/query";

    }
}
