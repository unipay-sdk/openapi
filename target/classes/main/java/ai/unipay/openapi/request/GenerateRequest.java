package ai.unipay.openapi.request;

/**
 * 支付请求基类
 */
public interface GenerateRequest<T> {
    /**
     * 返回参数的实体对象
     * 根据实际初始化的接口返回参数的实体对象
     *
     * @return 实体对象
     */
    Class<T> getResponseClass();

    /**
     * 接口
     * 根据实际初始化的接口返回不同接口的通讯接口地址
     *
     * @return 接口通讯接口地址
     */
    String getServerUrl();

}
