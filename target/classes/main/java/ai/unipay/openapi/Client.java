package ai.unipay.openapi;

import ai.unipay.openapi.request.GenerateRequest;
import ai.unipay.openapi.response.GenerateResponse;

/**
 * 请求相关处理
 * Created by zhenghang
 */
public interface Client {
    /**
     * 发起请求
     *
     * @param var1 请求参数
     * @param <T>  返回对象
     * @return 请求返回信息
     */
    <T extends GenerateResponse> GenerateResponse execute(GenerateRequest<T> var1);
}
