package ai.unipay.openapi.request.pay;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.pay.RefreshResponse;
import lombok.Data;

/**
 * 刷新/同步订单
 * 商户可以通过该接口主动查询同步订单状态
 * 需要调用接口的情况：
 * 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
 * 调用支付接口后，返回系统错误或未知交易状态情况；
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
public class RefreshRequest extends BaseRequest implements GenerateRequest<RefreshResponse> {

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
    public Class<RefreshResponse> getResponseClass() {
        return RefreshResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/refresh";

    }
}
