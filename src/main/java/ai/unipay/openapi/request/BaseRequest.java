package ai.unipay.openapi.request;

import lombok.Data;

/**
 * 请求对象基类
 * 存放接口每次必须的提交公有属性
 *
 * @author chenwenjun
 * @version 1.0
 * @date 2018/7/10
 */
@Data
public class BaseRequest {

    /**
     * appid
     * 获取appid、appsecret ：
     * 商户：
     * 商户平台 -> 开放平台 -> 应用列表 -> 新增应用 -> 提交审核 ->（服务商）审核通过后即可使用
     * 运营商、渠道商（线下提供）：
     * 功能扩展 –> 开放应用 ->新增应用
     * 必填
     */
    private String appid;
    /**
     * 随机数
     * 随机字符串，长度要求为32位字符串
     * 必填
     */
    private String nonceStr;
    /**
     * 版本号
     * 接口的版本号
     * 必填
     */
    private String version = "V1.0";
    /**
     * 机构号
     * 机构号，用于识别渠道商，运营商
     * 必填
     */
    private String orgId;

}
