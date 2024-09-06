package com.liev.clouds.utils;

import com.liev.clouds.config.ProxyConfig;
import com.liev.clouds.dao.HttpResponse;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpUtils 工具类，提供发送 HTTP 请求的方法。
 *  @author Revcloud
 * @since 2024/7/26 15:54
 */
public class HttpUtils {

    private static ProxyConfig proxyConfig;

    /**
     * 设置代理配置。
     *
     * @param proxyConfig 代理配置对象
     */
    public static void setProxyConfig(ProxyConfig proxyConfig) {
        HttpUtils.proxyConfig = proxyConfig;
    }

    /**
     * 发送 POST 请求。
     *
     * @param urlString 请求的 URL
     * @param postData 请求体数据
     * @param headers 自定义请求头
     * @return HttpResponse 对象，包含响应码、响应体和响应头
     */
    public static HttpResponse sendPost(String urlString, String postData, Map<String, String> headers) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(urlString);
            post.setEntity(new StringEntity(postData, "UTF-8"));

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    // 确保不设置Host头，Host头由HttpClient自动设置
                    if (!"Host".equalsIgnoreCase(entry.getKey())) {
                        post.setHeader(entry.getKey(), entry.getValue());
                    }
                }
            }

            if (proxyConfig != null && proxyConfig.getProxy() != Proxy.NO_PROXY) {
                HttpHost proxy = new HttpHost(proxyConfig.getProxyHost(), proxyConfig.getProxyPort(), proxyConfig.getProxyType().toLowerCase());
                RequestConfig config = RequestConfig.custom()
                        .setProxy(proxy)
                        .build();
                post.setConfig(config);
            }

            try (CloseableHttpResponse response = client.execute(post)) {

                int responseCode = response.getStatusLine().getStatusCode();

                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

                Map<String, String> responseHeaders = new HashMap<>();
                for (Header header : response.getAllHeaders()) {
                    responseHeaders.put(header.getName(), header.getValue());
                }

                return new HttpResponse(responseCode, responseBody, responseHeaders);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse(-1, "请求失败: " + e.getMessage(), new HashMap<>());
        }
    }

    /**
     * 发送GET请求
     * @param urlString 请求的 URL
     * @param headers 自定义请求头
     * @return HttpResponse 对象，包含响应码、响应体和响应头
     */
    public static HttpResponse sendGet(String urlString, Map<String, String> headers) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(urlString);


            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    // 确保不设置Host头，Host头由HttpClient自动设置
                    if (!"Host".equalsIgnoreCase(entry.getKey())) {
                        get.setHeader(entry.getKey(), entry.getValue());
                    }
                }
            }


            if (proxyConfig != null && proxyConfig.getProxy() != Proxy.NO_PROXY) {
                HttpHost proxy = new HttpHost(proxyConfig.getProxyHost(), proxyConfig.getProxyPort(), proxyConfig.getProxyType().toLowerCase());
                RequestConfig config = RequestConfig.custom()
                        .setProxy(proxy)
                        .build();
                get.setConfig(config);
            }

            try (CloseableHttpResponse response = client.execute(get)) {

                int responseCode = response.getStatusLine().getStatusCode();

                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

                Map<String, String> responseHeaders = new HashMap<>();
                for (Header header : response.getAllHeaders()) {
                    responseHeaders.put(header.getName(), header.getValue());
                }

                return new HttpResponse(responseCode, responseBody, responseHeaders);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse(-1, "请求失败: " + e.getMessage(), new HashMap<>());
        }
    }

}
