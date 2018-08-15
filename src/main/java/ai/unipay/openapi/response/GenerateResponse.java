package ai.unipay.openapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 公共返回参数
 *
 * @author zhenghang
 * @Date: 2018-05-24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateResponse {
    /**
     * 状态码
     * 业务返回码
     */
    private String code;
    /**
     * 错误提示
     * 业务返异常的描述
     */
    @JsonProperty("err_msg")
    private String errMsg;
    /**
     * 签名，根据全部返回参数进行签名（全部并非只有文档中的参数）
     */
    @JsonProperty("open_sign")
    private String openSign;
    /**
     * 请求时的appid
     */
    @JsonProperty("open_appid")
    private String openAppid;
}
