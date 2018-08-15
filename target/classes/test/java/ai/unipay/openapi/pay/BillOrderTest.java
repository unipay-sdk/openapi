package ai.unipay.openapi.pay;

import ai.unipay.openapi.DefaultClient;
import ai.unipay.openapi.request.pay.BillOrderRequest;
import ai.unipay.openapi.response.pay.BillOrderResponse;
import ai.unipay.openapi.utils.RandomDigital;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 订单对账示例
 *
 * @author zhenghang
 * @version 版本号：V1.0
 * @Date: 2018-05-30
 */
public class BillOrderTest {
    /**
     * 初始化APPID
     */
    private String APPID = "hf1020354855HWMA";
    /**
     * 初始化APPSECRET
     */
    private String APPSECRET = "dgyFFJJfVhXdQo9lNXxbvrNJs500AjKA";
    /**
     * 初始化SERVERURL
     */
    private String SERVERURL = "http://localhost:8084";

    /**
     * 初始化运、渠APPID
     */
    private String ORG_APPID = "cj59064f53-1693-48";
    /**
     * 初始化运、渠APPSECRET
     */
    private String ORG_APPSECRET = "66d28621-ec66-48ac-b50e-a5cc22e4ed1f";
    /**
     * 初始化机构号
     */
    private String ORG_ID = "15738240261573824026";

    /**
     * 订单对账示例
     * 说明：
     * 商户系统可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和系统侧数据不一致，通过对账单核对后可校正支付状态。
     * <p>
     * 支付流程介绍
     * 步骤1：通过该接口获取对账单列表；
     * 步骤2：通过对账单列表进行比对数据；
     * <p>
     * 适用场景：
     * 1：订单对账
     * <p>
     * 接入流程
     * 1：获取appid、appsecret
     * 商户：
     * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
     * 运营商、渠道商（线下提供）：
     * 功能扩展 –> 开放应用 ->新增应用
     * <p>
     * 2：a.集成SDK根据接口文档快速接入实现快速开发
     * ①. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API；
     * ②.确定接口对应的类；
     * 例如接口通信地址：/haipay/bill-order
     * 在SDK中对应的类为：每个接口名首字母大写，并去掉分隔符（“-”），末尾加上Request（或Response）
     * 如上接口名对应的类为：
     * BillOrderRequest（请求类）
     * BillOrderResponse（响应类）
     * <p>
     * b.根据接口文档文档接入
     * ①.根据文档初始化发起请求的相关参数
     * ②.根据发起请求的相关参数进行签名（详见文档签名）
     * ③.发起请求
     * ④.根据文档对应返回值并进行相关逻辑处理
     */
    /**
     * 商户接入
     */
    @Test
    public void billOrder() {
        BillOrderRequest billOrderRequest = new BillOrderRequest();
        /**
         * appid
         * 获取appid、appsecret ：
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        billOrderRequest.setAppid(APPID);
        /**
         * 对账单日期 格式 : 年-月-日   示例 : 2017-05-05
         * 获取对账单的日期
         * 必填
         */
        billOrderRequest.setTime("2018-07-21");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        billOrderRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 初始化链接和appSecret
         *
         * @param serverUrl 域名链接
         * @param appSecret appSecret
         */
        DefaultClient defaultClient = new DefaultClient(SERVERURL, APPSECRET);
        /**
         * 初始化信息并发起请求
         *
         * @param microPayRequest 请求信息
         * @return 支付结果
         */

        BillOrderResponse billOrderResponse = defaultClient.execute(billOrderRequest);

        System.out.println("返回：" + JSON.toJSONString(billOrderResponse));
    }

    /**
     * 运、渠接入
     */
    @Test
    public void orgBillOrder() {
        BillOrderRequest billOrderRequest = new BillOrderRequest();
        /**
         * appid
         * 获取appid、appsecret ：
         * 商户：
         * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
         * 运营商、渠道商（线下提供）：
         * 功能扩展 –> 开放应用 ->新增应用
         * 必填
         */
        billOrderRequest.setAppid(ORG_APPID);
        /**
         * 机构号
         * 机构号，用于识别渠道商，运营商
         * 必填
         */
        billOrderRequest.setOrgId(ORG_ID);
        /**
         * 商户号
         * 商户号，系统生成的商户号，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
        billOrderRequest.setMerchantNo("1573824026");
        /**
         * 外部商户号
         * 外部商户号，由运营商、渠道商自定义生成，用于识别商户，商户号和外部商户号特殊选填二选一
         * 特殊必填
         */
//        billOrderRequest.setOutMerchantNo("");
        /**
         * 对账单日期 格式 : 年-月-日   示例 : 2017-05-05
         * 获取对账单的日期
         * 必填
         */
        billOrderRequest.setTime("2018-07-20");
        /**
         * 随机数
         * 随机字符串，长度要求为32位字符串
         * 必填
         */
        billOrderRequest.setNonceStr(RandomDigital.randomOnlyNumber(32));
        /**
         * 初始化链接和appSecret
         *
         * @param serverUrl 域名链接
         * @param appSecret appSecret
         */
        DefaultClient defaultClient = new DefaultClient(SERVERURL, ORG_APPSECRET);
        /**
         * 初始化信息并发起请求
         *
         * @param microPayRequest 请求信息
         * @return 支付结果
         */
        BillOrderResponse billOrderResponse = defaultClient.execute(billOrderRequest);
        System.out.println("返回：" + JSON.toJSONString(billOrderResponse));
    }
}
