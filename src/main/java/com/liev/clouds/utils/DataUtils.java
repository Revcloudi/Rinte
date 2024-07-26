package com.liev.clouds.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author Revcloud
 * @since 2024/7/26 17:14
 */
public class DataUtils {
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

}
