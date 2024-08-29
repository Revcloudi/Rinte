package com.liev.clouds.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.liev.clouds.dao.HttpResponse;
import javafx.scene.control.TextArea;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Revcloud
 * @since 2024/7/26 17:14
 */
public class DataUtils {

    /**
     * 格式化JSON
     * @param json
     * @return
     */
    public static String formatJson(String json){
        try {
            JSONObject jsonObj = JSON.parseObject(json);
            return JSON.toJSONString(jsonObj, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            return "Invalid JSON: " + json;
        }
    }

    public static JSONObject parseJson(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 检测漏洞是否存在
     * @param responseBody 响应体
     * @param regex  正则检测
     * @param expectedValue  需要检测的字符
     * @return
     */
    public static boolean checkVulnerability(String responseBody, String regex, String expectedValue) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);
        if (matcher.find()) {
            String dataValue = matcher.group(1);
            return dataValue.equals(expectedValue);
        }
        return false;
    }

    /**
     * AjReport 将响应进行封装
     * @param postTxt
     * @param responseArea
     * @param outComeArea
     * @param postData
     * @param response
     * @param resultMessage
     */
    public static void displayResponse(TextArea postTxt, TextArea responseArea, TextArea outComeArea, String postData, HttpResponse response, String resultMessage) {
        String formattedPostData = formatJson(postData);
        postTxt.appendText(formattedPostData);

        Map<String, String> responseHeaders = response.getResponseHeaders();
        StringBuilder headersStringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
            headersStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        responseArea.appendText(response.getResponseCode() + "\n" + headersStringBuilder.toString() + "\n" + response.getResponseBody());

        outComeArea.appendText(resultMessage);
    }


}
