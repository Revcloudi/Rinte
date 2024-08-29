package com.liev.clouds.webcontroller.framework;

import com.liev.clouds.payload.RequestHeaderPayload;
import com.liev.clouds.payload.RuoYiPayload;
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
    public static TextArea log;

    @FXML
    public static TextArea responseBody;

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

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put(header, body);
        headersMap.put("User-Agent", RequestHeaderPayload.CHROME_WINDOWS_11);

        switch (expType){
            case "ALL":
                sql_system_role_list(urls, headersMap);
                sql_system_dept_edit(urls, headersMap);
                sql_tool_gen_createTable(urls, headersMap);
            case "RuoYi 小于 4.6.2 SQL注入 CVE-2023-49371":
                sql_system_role_list(urls, headersMap);
                sql_system_dept_edit(urls, headersMap);
                break;
            case "RuoYi 小于 4.7.5 SQL注入 CVE-2022-48114":
                sql_tool_gen_createTable(urls, headersMap);
                break;
            case "RuoYi 等于 4.7.2 定时任务RCE":
                rce_Jndi_snakeyaml(urls, headersMap);
                break;
        }
    }

}
