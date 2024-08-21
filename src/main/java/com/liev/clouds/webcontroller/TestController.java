package com.liev.clouds.webcontroller;

import com.liev.clouds.config.ProxyConfig;
import com.liev.clouds.exp.shiro.AttackService;
import com.liev.clouds.logs.proxylog.ProxyLogger;
import com.liev.clouds.payload.shiro.util.HttpUtil;
import com.liev.clouds.utils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Revcloud
 * @since 2024/8/13 19:44
 */
public class TestController {

    @FXML
    public RadioButton disableCheckBox;

    @FXML
    public RadioButton automaticProxyConfiguration;

    @FXML
    public CheckBox automaticallyURL;

    @FXML
    public TextField proxyUrl;

    @FXML
    public Button cleanPassword;

    @FXML
    public RadioButton enableCheckBox;

    @FXML
    public RadioButton httpCheckBox;

    @FXML
    public RadioButton socksCheckBox;

    @FXML
    public TextField ipAddressField;

    @FXML
    public TextField portField;

    @FXML
    public TextField noProxy;

    @FXML
    public CheckBox proxyAuthentication;

    @FXML
    public TextField usernameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button checkConnection;

    @FXML
    public Button cancelButton;

    @FXML
    public Button saveButton;

    private ProxyConfig proxyConfig;

    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Test.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane();
        }
    }

    @FXML
    public void initialize(){
        proxyConfig = new ProxyConfig();

        disableCheckBox.setOnAction(event -> {
            if(disableCheckBox.isSelected()){
                automaticProxyConfiguration.setSelected(false);
                enableCheckBox.setSelected(false);
            }
        });

        automaticProxyConfiguration.setOnAction(event -> {
            if (automaticProxyConfiguration.isSelected()){
                disableCheckBox.setSelected(false);
                enableCheckBox.setSelected(false);
            }
        });

        enableCheckBox.setOnAction(event -> {
            if (enableCheckBox.isSelected()){
                disableCheckBox.setSelected(false);
                automaticProxyConfiguration.setSelected(false);
            }
        });

        httpCheckBox.setOnAction(event -> {
            if (httpCheckBox.isSelected()){
                socksCheckBox.setSelected(false);
            }
        });

        socksCheckBox.setOnAction(event -> {
            if (socksCheckBox.isSelected()){
                httpCheckBox.setSelected(false);
            }
        });
    }

    @FXML
    public void cleanPassword(ActionEvent actionEvent) {
    }

    @FXML
    public boolean checkConnection(ActionEvent actionEvent) {
        boolean check = false;
        try{
            URL url = new URL("https://www.baidu.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode == 200){
                ProxyLogger.connect_success();
                check = true;
            }else{
                ProxyLogger.error();
            }

        }catch (Exception e){
            ProxyLogger.error_out(e);
        }

        return check;
    }

    @FXML
    public void cancelButton(ActionEvent actionEvent) {
        proxyUrl.clear();
        ipAddressField.clear();
        portField.clear();
        noProxy.clear();
        usernameField.clear();
        passwordField.clear();

        httpCheckBox.setSelected(true);
        disableCheckBox.setSelected(true);

        proxyConfig.clearProxy();

        HttpUtils.setProxyConfig(null);
        HttpUtil.setProxyConfig(null);
        AttackService.setProxyConfig(null);

        ProxyLogger.proxy_clear();
    }

    /**
     * 保存代理配置
     * @param actionEvent
     */
    @FXML
    public void saveButton(ActionEvent actionEvent) {
        if (enableCheckBox.isSelected() && httpCheckBox.isSelected()){
            String ip = ipAddressField.getText();
            String port = portField.getText();
            if (ip == null || ip.isEmpty() || port == null || port.isEmpty()){
                ProxyLogger.error_null();
                return;
            }

            proxyConfig.setProxy(ip, Integer.parseInt(port), "HTTP", usernameField.getText(), passwordField.getText());

            HttpUtils.setProxyConfig(proxyConfig);
            HttpUtil.setProxyConfig(proxyConfig);
            AttackService.setProxyConfig(proxyConfig);

            ProxyLogger.success();
        }else if(automaticProxyConfiguration.isSelected()){
            String url = automaticallyURL.getText();
            //TODO url代理直接连接逻辑

        }else if(enableCheckBox.isSelected() && socksCheckBox.isSelected()){
            //TODO SOCKS代理连接逻辑待增加
        }else {

        }
    }
}
