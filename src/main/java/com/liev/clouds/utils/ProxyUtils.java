package com.liev.clouds.utils;

import com.liev.clouds.webcontroller.setting.ProxySettingsController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author Revcloud
 * @since 2024/8/31 15:20
 */
public class ProxyUtils {

    /**
     * 将配置保存到配置文件中
     * @param controller
     */
    public static void saveProxySettings(ProxySettingsController controller) {
        String userHome = System.getProperty("user.home");
        Path configPath = Paths.get(userHome, "proxy-settings.properties");

        Properties properties = new Properties();
        properties.setProperty("proxy.enabled", Boolean.toString(controller.enableCheckBox.isSelected()));
        properties.setProperty("proxy.type", controller.httpCheckBox.isSelected() ? "HTTP" : "SOCKS");
        properties.setProperty("proxy.ip", controller.ipAddressField.getText());
        properties.setProperty("proxy.port", controller.portField.getText());
        properties.setProperty("proxy.username", controller.usernameField.getText());
        properties.setProperty("proxy.password", controller.passwordField.getText());
        properties.setProperty("automaticProxy.selected", Boolean.toString(controller.automaticProxyConfiguration.isSelected()));
        properties.setProperty("disableProxy.selected", Boolean.toString(controller.disableCheckBox.isSelected()));
        properties.setProperty("httpProxy.selected", Boolean.toString(controller.httpCheckBox.isSelected()));
        properties.setProperty("socksProxy.selected", Boolean.toString(controller.socksCheckBox.isSelected()));
        properties.setProperty("automaticallyURL", controller.automaticallyURL.getText());

        try {
            // 创建文件（如果文件不存在）
            if (!Files.exists(configPath)) {
                Files.createFile(configPath);
            }

            // 保存属性到文件
            try (FileOutputStream output = new FileOutputStream(configPath.toFile())) {
                properties.store(output, "Proxy Settings and UI States");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取代理配置
     * @param controller
     */
    public static void loadProxySettings(ProxySettingsController controller) {
        // 获取用户主目录路径
        String userHome = System.getProperty("user.home");
        Path configPath = Paths.get(userHome, "proxy-settings.properties");

        Properties properties = new Properties();

        if (Files.exists(configPath)) {
            try (FileInputStream input = new FileInputStream(configPath.toFile())) {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 恢复保存的代理设置
            boolean proxyEnabled = Boolean.parseBoolean(properties.getProperty("proxy.enabled", "false"));
            String proxyType = properties.getProperty("proxy.type", "HTTP");
            String proxyIp = properties.getProperty("proxy.ip", "");
            String proxyPort = properties.getProperty("proxy.port", "");
            String proxyUsername = properties.getProperty("proxy.username", "");
            String proxyPassword = properties.getProperty("proxy.password", "");
            boolean automaticProxySelected = Boolean.parseBoolean(properties.getProperty("automaticProxy.selected", "false"));
            boolean disableProxySelected = Boolean.parseBoolean(properties.getProperty("disableProxy.selected", "false"));
            boolean httpProxySelected = Boolean.parseBoolean(properties.getProperty("httpProxy.selected", "true"));
            boolean socksProxySelected = Boolean.parseBoolean(properties.getProperty("socksProxy.selected", "false"));
            String automaticallyUrlText = properties.getProperty("automaticallyURL", "");

            // 应用这些设置到UI
            controller.enableCheckBox.setSelected(proxyEnabled);
            controller.httpCheckBox.setSelected("HTTP".equals(proxyType) && httpProxySelected);
            controller.socksCheckBox.setSelected("SOCKS".equals(proxyType) && socksProxySelected);
            controller.ipAddressField.setText(proxyIp);
            controller.portField.setText(proxyPort);
            controller.usernameField.setText(proxyUsername);
            controller.passwordField.setText(proxyPassword);
            controller.automaticProxyConfiguration.setSelected(automaticProxySelected);
            controller.disableCheckBox.setSelected(disableProxySelected);
            controller.automaticallyURL.setText(automaticallyUrlText);

        } else {
            System.out.println("Proxy settings file not found. Skipping loading.");
        }
    }
}
