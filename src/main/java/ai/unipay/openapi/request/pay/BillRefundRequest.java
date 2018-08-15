package ai.unipay.openapi.request.pay;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.pay.BillRefundResponse;
import lombok.Data;

/**
 * 退款详情查询
 * 用于查询订单退款的明细列表，配合对账使用
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class BillRefundRequest extends BaseRequest implements GenerateRequest<BillRefundResponse> {

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
     * 商户支付的订单号由商户自定义生成，支付要求商户订单号保持唯一性（建议根据当前系统时间戳加随机序列来生成订单号）
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
    public Class<BillRefundResponse> getResponseClass() {
        return BillRefundResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/bill-refund";

    }
}
