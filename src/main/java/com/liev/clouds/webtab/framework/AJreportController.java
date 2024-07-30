package com.liev.clouds.webtab.framework;

import com.liev.clouds.dao.AjReportDetectionParams;
import com.liev.clouds.exp.AjReportExp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AJreportController {

    @FXML
    private TextField inUrl;

    @FXML
    private Button button;

    @FXML
    private CheckBox checkBoxPost;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea postTxt;

    @FXML
    private TextArea outComeArea;

    @FXML
    private TextArea responseArea;

    @FXML
    public void initialize() {
        comboBox.getSelectionModel().selectFirst();

        button.setOnAction(event -> {
            String url = inUrl.getText();
            List<String> postData = checkBoxPost.isSelected() ? Arrays.asList(postTxt.getText()) : null;
            String selectedValue = comboBox.getSelectionModel().getSelectedItem();

            AjReportExp ajReportExp = new AjReportExp();
            if (selectedValue.equals("All")) {
                ajReportExp.processDetection(new AjReportDetectionParams(url, postData, postTxt, outComeArea, responseArea));
            } else if (selectedValue.equals("CVE-2024-5352(任意命令执行)")) {
                ajReportExp.processRce(new AjReportDetectionParams(url, postData, postTxt, outComeArea, responseArea));
            } else if (selectedValue.equals("CVE-2024-5350(SQL信息泄露)")) {
                ajReportExp.processSql(new AjReportDetectionParams(url, postData, postTxt, outComeArea, responseArea));
            } else if (selectedValue.equals("CVE-2024-5356(任意命令执行)")) {
                ajReportExp.processJsExp(new AjReportDetectionParams(url, postData, postTxt, outComeArea, responseArea));
            }
        });
    }

    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AJreportTab.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(); // Return an empty pane if loading fails
        }
    }
}
