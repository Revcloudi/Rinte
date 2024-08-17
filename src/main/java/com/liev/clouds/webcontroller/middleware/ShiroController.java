package com.liev.clouds.webcontroller.middleware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShiroController{

    @FXML
    private TextField url;

    @FXML
    private TextField timeOut;

    @FXML
    private ComboBox<String> requestMethod;

    @FXML
    private Button check;

    @FXML
    private TextField requestHeader;

    @FXML
    private TextField requestBody;

    @FXML
    private TextField keyWord;

    @FXML
    private TextField specifyKey;

    @FXML
    private CheckBox encryption;

    @FXML
    private Button detectingKeys;

    @FXML
    private Button blastingKey;

    @FXML
    private ComboBox<String> utilizeChain;

    @FXML
    private ComboBox<String> echoWay;

    @FXML
    private Button detectingUtilizationChain;

    @FXML
    private Button blastingUtilizationChain;

    @FXML
    private TextArea log;

    @FXML
    private TextField command;

    @FXML
    private Button execute;

    @FXML
    private TextArea executionResults;

    @FXML
    private ComboBox<String> shellType;

    @FXML
    private TextField shellPath;

    @FXML
    private TextField shellPassword;

    @FXML
    private Button shellInject;

    @FXML
    private Button randomGenerationKey;

    @FXML
    private TextArea keyLog;

    @FXML
    public void initialize(){
        this.initComBoBox();

    }


    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Shiro.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(); // Return an empty pane if loading fails
        }
    }

    public void initComBoBox(){
        requestMethod.getSelectionModel().selectFirst();
        utilizeChain.getSelectionModel().selectFirst();
        echoWay.getSelectionModel().selectFirst();
        shellType.getSelectionModel().selectFirst();


    }

    /**
     * 密钥检测
     * @param actionEvent
     */
    public void detectingKeys(ActionEvent actionEvent) {
        String shiroKey = specifyKey.getText();
        String checkHost = url.getText();
        String httpTimeOut = timeOut.getText();

        //自定义请求头
        Map<String, String> myHeader= new HashMap<>() ;
        if(!requestHeader.getText().equals("")) {
            String[] headers = requestHeader.getText().split("&&&");
            for (String s : headers) {
                String[] header = s.split(":", 2);
                if (header[0].toLowerCase().equals("cookie")) {
                    myHeader.put("Cookie", header[1]);
                } else {
                    myHeader.put(header[0], header[1]);
                }
            }
        }

        String postData = (String)requestBody.getText();
        String reqMethod = (String)requestMethod.getValue();

    }
}

