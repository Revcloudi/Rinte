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
     * 从响应头中检查字符，存在为true
     * @param headers 需要检查的响应头
     * @param expectedValue 检测的字符
     * @return 是否存在
     */
    public static boolean containsHeader(Map<String, String> headers, String expectedValue){

        for (String headerName : headers.keySet()) {

            if (headerName.contains(expectedValue)) {
                return true;
            }
        }
        return false;
    }


}
