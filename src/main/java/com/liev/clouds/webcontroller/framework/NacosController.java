package com.liev.clouds.webcontroller.framework;

import com.liev.clouds.exp.NacosExp;
import com.liev.clouds.payload.NacosPayloda;
import com.liev.clouds.payload.RequestHeaderPayload;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Revcloud
 * @since 2024/8/31 14:58
 */
public class NacosController {

    @FXML
    public TextField url;

    @FXML
    public ComboBox<String> options;

    @FXML
    public TextField requestHeader;

    @FXML
    public TextField requestBody;

    @FXML
    public TextArea log;

    @FXML
    public TextArea responseBody;

    @FXML
    public void initialize(){
        options.getSelectionModel().selectFirst();
    }

    @FXML
    public void check(ActionEvent actionEvent) {
        String urls = url.getText();
        String expType = options.getSelectionModel().getSelectedItem();
        String header = requestHeader.getText();
        String body = requestBody.getText();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("User-Agent", NacosPayloda.USER_AGENT);
        headersMap.put(header, body);

        switch (expType){
            case "Nacos认证绕过 CVE-2021-29441":
                NacosExp.check(urls, headersMap, this);
                break;
            case "Nacos 未授权接口命令执行漏洞 CVE-2021-29442":
                NacosExp.exploit(urls, headersMap, this);
                break;
        }
    }

    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Nacos.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane();
        }
    }
}
