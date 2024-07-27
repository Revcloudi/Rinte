package com.liev.clouds.dao;

import java.util.Map;

/**
 * @author Revcloud
 * @since 2024/7/26 16:00
 */
public class HttpResponse {
    private final int responseCode;
    private final String responseBody;
    private final Map<String, String> responseHeaders;

    public HttpResponse(int responseCode, String responseBody, Map<String, String> responseHeaders) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
        this.responseHeaders = responseHeaders;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }
}
