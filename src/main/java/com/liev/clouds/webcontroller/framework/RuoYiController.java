package com.liev.clouds.webcontroller.framework;

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
import java.util.Objects;

import static com.liev.clouds.exp.RuoYiExp.*;

/**
 * @author Revcloud
 * @since 2024/7/30 17:36
 */
public class RuoYiController {

    @FXML
    public ComboBox<String> options;

    @FXML
    public TextField url;

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

    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RuoYi.fxml"));
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

        if(Objects.equals(body, "")){
            log.setText("请输入Cookie再进行检测！");
            return;
        }

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headersMap.put("User-Agent", RequestHeaderPayload.CHROME_WINDOWS_11);
        headersMap.put(header, body);

        switch (expType){
            case "ALL":
                sql_system_role_list(urls, headersMap, this);
                sql_system_dept_edit(urls, headersMap, this);
                sql_tool_gen_createTable(urls, headersMap, this);
            case "RuoYi 小于 4.6.2 SQL注入 CVE-2023-49371":
                sql_system_role_list(urls, headersMap, this);
                sql_system_dept_edit(urls, headersMap, this);
                break;
            case "RuoYi 小于 4.7.5 SQL注入 CVE-2022-48114":
                sql_tool_gen_createTable(urls, headersMap, this);
                break;
            case "RuoYi 等于 4.7.2 定时任务RCE":
                rce_Jndi_snakeyaml(urls, headersMap, this);
                break;
            case "RuoYi 小于 4.7.3 文件上传解析HTML":
                upload_Html_rce(urls, headersMap, this);
                break;
        }
    }

}
