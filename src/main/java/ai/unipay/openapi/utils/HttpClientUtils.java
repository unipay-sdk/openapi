package ai.unipay.openapi.utils;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpClient请求工具类
 *
 * @author zhenghang
 */
public class HttpClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
    // 请求超时时间
    private static final int CONNECT_TIME_OUT = 30000;
    // 读取超时
    private static final int READ_TIME_OUT = 80000;

    private static final Map<String, CloseableHttpClient> HOST_CLOSEABLE_HTTP_CLIENT_MAP = new HashMap<>(2 << 3);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            HOST_CLOSEABLE_HTTP_CLIENT_MAP.forEach((s, closeableHttpClient) -> {
                if (closeableHttpClient != null) {
                    try {
                        closeableHttpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }));
    }

    public static final RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(5000).build();
    }

    private static final CloseableHttpClient getCloseableHttpClient(String url) {
        if (url == null) {
            throw new IllegalArgumentException("url不能为null");
        }
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("new URI() error!", e);
        }
        String host = uri.getHost();
        CloseableHttpClient client = HOST_CLOSEABLE_HTTP_CLIENT_MAP.get(host);
        if (client == null) {
            synchronized (HttpClientUtils.class) {
                client = HOST_CLOSEABLE_HTTP_CLIENT_MAP.get(host);
                if (client != null) {
                    return client;
                }
                PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                //将最大总连接数增加到
                cm.setMaxTotal(30);
                //将每条路由的默认最大连接数增加到
                cm.setDefaultMaxPerRoute(10);
                client = HttpClients.custom()
                        .setDefaultRequestConfig(requestConfig())
                        .setConnectionManager(cm)
                        .build();
                HOST_CLOSEABLE_HTTP_CLIENT_MAP.put(host, client);
                return client;
            }
        }
        return client;

    }

    public static final RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECT_TIME_OUT)
                .setConnectTimeout(CONNECT_TIME_OUT)
                .setSocketTimeout(READ_TIME_OUT)
                .build();
    }

    /**
     * Post请求
     * 以表单形式提交
     *
     * @param url
     * @param params 请求参数，例如 key1=value1&key2=vlaue2...
     * @return
     */
    public static final RequestResult post(String url, String params) {
        HttpPost post = null;
        CloseableHttpResponse response = null;
        try {

            post = new HttpPost(url);
            post.setEntity(new StringEntity(params, ContentType.APPLICATION_FORM_URLENCODED.withCharset(Consts.UTF_8)));
            long in = System.currentTimeMillis();
            response = getCloseableHttpClient(url).execute(post);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return new RequestResult(true, HttpStatus.SC_OK, EntityUtils.toString(response.getEntity(), Consts.UTF_8));
            } else {
                return new RequestResult(false, response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            return processIOException(e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (post != null) {
                post.releaseConnection();
            }
        }
    }


    public static final RequestResult processIOException(IOException e) {
        if (e instanceof SocketTimeoutException) {
            return new RequestResult(false, 303, "读取返回内容超时");
        } else if (e instanceof ConnectTimeoutException) {
            return new RequestResult(false, 301, "请求连接超时");
        } else if (e instanceof NoHttpResponseException) {
            return new RequestResult(false, 301, e.getMessage());
        }
        LOGGER.error("httpClientUtils-", e);
        return new RequestResult(false, 100, "请求失败");
    }

    public static class RequestResult {
        public final boolean result;
        public final int status;
        public final String content;

        public RequestResult(boolean result, int status) {
            this.result = result;
            this.status = status;
            this.content = "";
        }

        public RequestResult(boolean result, int status, String content) {
            this.result = result;
            this.status = status;
            this.content = content;
        }

        @Override
        public String toString() {
            return "[result=" + result + ", status=" + status + ", content=" + content + "]";
        }
    }

    /**
     * Map转Url参数
     *
     * @param map 请求参数Map
     * @return Url参数字符串
     */
    public static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue());
            stringBuffer.append("&");
        }
        String toString = stringBuffer.toString();
        if (toString.endsWith("&")) {
            toString = toString.substring(0, toString.length() - 1);
        }
        return toString;
    }
}
