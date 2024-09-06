package com.liev.clouds.webcontroller.framework;

import com.liev.clouds.exp.AjReportExp;
import com.liev.clouds.payload.RequestHeaderPayload;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AJreportController {

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
    public void initialize() {
        options.getSelectionModel().selectFirst();
    }

    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AJreport.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane();
        }
    }

    @FXML
    public void check(ActionEvent actionEvent) {
        String urls = url.getText();
        String expType = options.getSelectionModel().getSelectedItem();
        String header = requestHeader.getText();
        String body = requestBody.getText();

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json;charset=UTF-8");
        headersMap.put("User-Agent", RequestHeaderPayload.CHROME_WINDOWS_11);
        headersMap.put(header, body);

        switch (expType){
            case "CVE-2024-5352(任意命令执行)":
                AjReportExp.RCE_dataSetParam_verification(urls, headersMap, this);
                break;
            case "CVE-2024-5356(任意命令执行)":
                AjReportExp.RCE_dataSet_testTransform(urls, headersMap, this);
                break;
            case "弱口令":
                AjReportExp.weak_password(urls, headersMap, this);
                break;
            case "SQL信息泄露":
                AjReportExp.information_leakage(urls, headersMap, this);
                break;
        }
    }
}
