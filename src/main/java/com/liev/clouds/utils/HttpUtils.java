package com.liev.clouds.utils;


import com.liev.clouds.dao.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.Header;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Revcloud
 * @since 2024/7/26 15:54
 */
public class HttpUtils {
    public static HttpResponse sendPost(String urlString, String postData, Map<String, String> headers) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(urlString);
            post.setEntity(new StringEntity(postData, "UTF-8"));

            // 设置自定义请求头
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    // 确保不设置Host头，Host头由HttpClient自动设置
                    if (!"Host".equalsIgnoreCase(entry.getKey())) {
                        post.setHeader(entry.getKey(), entry.getValue());
                    }
                }
            }

            try (CloseableHttpResponse response = client.execute(post)) {
                int responseCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

                // 获取所有响应头
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
