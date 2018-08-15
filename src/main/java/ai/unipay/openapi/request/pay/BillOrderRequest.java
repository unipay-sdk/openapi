package ai.unipay.openapi.request.pay;

import ai.unipay.openapi.request.BaseRequest;
import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.pay.BillOrderResponse;
import lombok.Data;

/**
 * 订单对账
 * 商户可以通过该接口获取历史交易清单。比如掉单、系统错误等导致商户侧和系统侧数据不一致，通过对账单核对后可校正支付状态。
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-24
 */
@Data
public class BillOrderRequest extends BaseRequest implements GenerateRequest<BillOrderResponse> {

    /**
     * 对账单日期 格式 : 年-月-日   示例 : 2017-05-05
     * 获取对账单的日期
     * 必填
     */
    private String time;
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
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 返回参数的实体对象
     */
    @Override
    public Class<BillOrderResponse> getResponseClass() {
        return BillOrderResponse.class;
    }

    /**
     * 通讯接口地址
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 通讯接口地址
     */
    @Override
    public String getServerUrl() {
        return "/haipay/bill-order";

    }
}
