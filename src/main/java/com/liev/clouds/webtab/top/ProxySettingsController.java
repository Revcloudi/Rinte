package com.liev.clouds.webtab.top;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Revcloud
 * @since 2024/7/31 9:50
 */
public class ProxySettingsController {

    @FXML
    private CheckBox enableCheckBox;

    @FXML
    private CheckBox disableCheckBox;

    @FXML
    private ComboBox<String> proxyTypeComboBox;

    @FXML
    private TextField ipAddressField;

    @FXML
    private TextField portField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextArea responseArea;

    @FXML
    public void initialize() {
        // 确保两个复选框只能一个被选中
        enableCheckBox.setOnAction(e -> {
            if (enableCheckBox.isSelected()) {
                disableCheckBox.setSelected(false);
            }
        });

        disableCheckBox.setOnAction(e -> {
            if (disableCheckBox.isSelected()) {
                enableCheckBox.setSelected(false);
            }
        });

        // 设置取消按钮的点击事件处理逻辑
        cancelButton.setOnAction(e -> handleCancel());
        // 设置保存按钮的点击事件处理逻辑
        saveButton.setOnAction(e -> handleSave());
    }

    private void handleCancel() {
        // 取消按钮的处理逻辑
        responseArea.clear();
        ipAddressField.clear();
        portField.clear();
        usernameField.clear();
        passwordField.clear();
        enableCheckBox.setSelected(false);
        disableCheckBox.setSelected(true);

        // 清除代理设置
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");
        System.clearProperty("http.proxyUser");
        System.clearProperty("http.proxyPassword");

        responseArea.appendText("已取消操作，表单已重置，代理设置已清除。\n");
    }

    private void handleSave() {
        responseArea.clear();
        if (enableCheckBox.isSelected() && "HTTP".equals(proxyTypeComboBox.getValue())) {
            String ip = ipAddressField.getText();
            String port = portField.getText();

            if (ip == null || ip.isEmpty() || port == null || port.isEmpty()) {
                responseArea.appendText("IP地址或端口不能为空！\n");
                return;
            }

            System.setProperty("http.proxyHost", ip);
            System.setProperty("http.proxyPort", port);
            System.setProperty("https.proxyHost", ip);
            System.setProperty("https.proxyPort", port);

            if (usernameField.getText() != null && !usernameField.getText().isEmpty()) {
                System.setProperty("http.proxyUser", usernameField.getText());
            } else {
                System.clearProperty("http.proxyUser");
            }

            if (passwordField.getText() != null && !passwordField.getText().isEmpty()) {
                System.setProperty("http.proxyPassword", passwordField.getText());
            } else {
                System.clearProperty("http.proxyPassword");
            }

            responseArea.appendText("代理设置成功：\n");
            responseArea.appendText("http.proxyHost: " + System.getProperty("http.proxyHost") + "\n");
            responseArea.appendText("http.proxyPort: " + System.getProperty("http.proxyPort") + "\n");
            responseArea.appendText("https.proxyHost: " + System.getProperty("https.proxyHost") + "\n");
            responseArea.appendText("https.proxyPort: " + System.getProperty("https.proxyPort") + "\n");

            verifyProxySettings();
        } else {
            responseArea.appendText("未启用代理或代理类型非HTTP。\n");
            // 取消代理设置
            System.clearProperty("http.proxyHost");
            System.clearProperty("http.proxyPort");
            System.clearProperty("https.proxyHost");
            System.clearProperty("https.proxyPort");
            responseArea.appendText("已取消代理设置。\n");
        }
    }

    private void verifyProxySettings() {
        try {
            URL url = new URL("http://www.lievclouds.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            responseArea.appendText("验证请求的响应代码：" + responseCode + "\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            responseArea.appendText("验证请求的内容：\n" + content.toString().substring(0, Math.min(content.length(), 200)) + "\n");
        } catch (Exception e) {
            responseArea.appendText("验证代理设置时出错：" + e.getMessage() + "\n");
        }
    }

    public StackPane getContent(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProxySettings.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(); // Return an empty pane if loading fails
        }
    }
}
