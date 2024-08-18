package com.liev.clouds.webcontroller.setting;

import com.liev.clouds.config.ProxyConfig;
import com.liev.clouds.exp.shiro.AttackService;
import com.liev.clouds.utils.HttpUtils;
import com.liev.clouds.payload.shiro.util.HttpUtil;
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
 * 代理设置控制器类，处理代理设置界面的交互逻辑。
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

    private ProxyConfig proxyConfig;

    @FXML
    public void initialize() {

        proxyTypeComboBox.getSelectionModel().selectFirst();

        proxyConfig = new ProxyConfig();

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

    /**
     * 处理取消操作，清除所有设置并重置表单。
     */
    private void handleCancel() {
        responseArea.clear();
        ipAddressField.clear();
        portField.clear();
        usernameField.clear();
        passwordField.clear();
        enableCheckBox.setSelected(false);
        disableCheckBox.setSelected(true);

        // 清除代理设置
        proxyConfig.clearProxy();


        HttpUtils.setProxyConfig(null);
        HttpUtil.setProxyConfig(null);
        AttackService.setProxyConfig(null);

        responseArea.appendText("已取消操作，表单已重置，代理设置已清除。\n");
    }

    /**
     * 处理保存操作，应用代理设置。
     */
    private void handleSave() {
        responseArea.clear();
        if (enableCheckBox.isSelected() && "HTTP".equals(proxyTypeComboBox.getValue())) {
            String ip = ipAddressField.getText();
            String port = portField.getText();

            if (ip == null || ip.isEmpty() || port == null || port.isEmpty()) {
                responseArea.appendText("IP地址或端口不能为空！\n");
                return;
            }

            proxyConfig.setProxy(ip, Integer.parseInt(port), proxyTypeComboBox.getValue(), usernameField.getText(), passwordField.getText());


            HttpUtils.setProxyConfig(proxyConfig);
            HttpUtil.setProxyConfig(proxyConfig);
            AttackService.setProxyConfig(proxyConfig);

            responseArea.appendText("代理设置成功：\n");
            responseArea.appendText("proxyHost: " + proxyConfig.getProxyHost() + "\n");
            responseArea.appendText("proxyPort: " + proxyConfig.getProxyPort() + "\n");
            responseArea.appendText("proxyType: " + proxyConfig.getProxyType() + "\n");

            verifyProxySettings();
        } else {
            responseArea.appendText("未启用代理或代理类型非HTTP。\n");
            proxyConfig.clearProxy();


            HttpUtils.setProxyConfig(null);
            HttpUtil.setProxyConfig(null);
            AttackService.setProxyConfig(null);

            responseArea.appendText("已取消代理设置。\n");
        }
    }

    /**
     * 验证代理设置是否生效。
     */
    private void verifyProxySettings() {
        try {
            URL url = new URL("https://www.lievclouds.com");
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

    /**
     * 加载并返回代理设置的界面。
     *
     * @return StackPane 包含代理设置界面
     */
    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProxySettings.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(); // 加载失败时返回一个空的 StackPane
        }
    }
}
