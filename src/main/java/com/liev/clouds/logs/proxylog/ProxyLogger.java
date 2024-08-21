package com.liev.clouds.logs.proxylog;

import javafx.scene.control.Alert;

/**
 * @author Revcloud
 * @since 2024/8/21 14:41
 */
public class ProxyLogger {

    public static void success(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("保存成功");
        alert.setHeaderText(null);
        alert.setContentText("代理保存成功！\n可使用BurpSuite抓取全流量");
        alert.showAndWait();
    }

    public static void error_null(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText("IP地址和端口不能为NULL！");
        alert.showAndWait();
    }

    public static void error_out(Exception e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText("错误:" + e.getMessage());
        alert.showAndWait();
    }

    public static void error(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText("代理连接错误！");
        alert.showAndWait();
    }

    public static void connect_success(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("连接正常");
        alert.setHeaderText(null);
        alert.setContentText("代理连接正常\n测试网址为https://www.baidu.com");
        alert.showAndWait();
    }

    public static void proxy_clear(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("清除代理");
        alert.setHeaderText(null);
        alert.setContentText("代理已经清除！");
        alert.showAndWait();
    }
}
