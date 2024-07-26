package com.liev.clouds.webtab;

import com.liev.clouds.exp.AjReportExp;
import javafx.scene.control.*;
import com.liev.clouds.interfaces.TabContent;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import javax.swing.plaf.ListUI;
import java.util.List;

public class AJreportTab implements TabContent {

    @Override
    public StackPane getContent() {
        StackPane stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();

        //顶部输入
        HBox topContainer = new HBox(10);
        topContainer.setPadding(new Insets(10));

        TextField inUrl = new TextField("http://127.0.0.1");
        inUrl.setPrefWidth(600);
        inUrl.setPrefHeight(30);

        Button button = new Button("检测");
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        button.setMinHeight(28);
        button.setMinWidth(50);

        CheckBox checkBoxPost = new CheckBox("自定义POST");
        checkBoxPost.setPrefWidth(100);
        checkBoxPost.setPrefHeight(28);
        checkBoxPost.setSelected(false);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("All","CVE-2024-5352(任意命令执行)","NULL");
        comboBox.setPrefWidth(200);
        comboBox.setPrefHeight(28);
        comboBox.getSelectionModel().selectFirst();


        topContainer.getChildren().addAll(inUrl, button, checkBoxPost, comboBox);

        // 中部显示区
        GridPane centerContainer = new GridPane();
        centerContainer.setPadding(new Insets(10));
        centerContainer.setHgap(10);
        centerContainer.setVgap(10);


        Label postCode = new Label("POST请求数据:");
        TextArea postTxt = new TextArea();
        postTxt.setPrefHeight(200);
        postTxt.setPrefWidth(1175);

        Label responseLabel = new Label("结果：");
        TextArea responseArea = new TextArea();
        responseArea.setPrefHeight(400);
        responseArea.setPrefWidth(1175);

        centerContainer.add(postCode,0,0);
        centerContainer.add(postTxt,0,1);
        centerContainer.add(responseLabel,0,2);
        centerContainer.add(responseArea,0,3);

        borderPane.setTop(topContainer);
        borderPane.setCenter(centerContainer);
        stackPane.getChildren().add(borderPane);

        button.setOnAction(event -> {
            //TODO 检测逻辑
            String url = inUrl.getText();
            String postData = checkBoxPost.isSelected() ? postTxt.getText() : null;
            String selectedValue = comboBox.getSelectionModel().getSelectedItem();

            if(selectedValue.equals("All")){
                AjReportExp ajReportExp = new AjReportExp();
                ajReportExp.processDetection(url, postData, postTxt, responseArea);
            }else if(selectedValue.equals("CVE-2024-5352(任意命令执行)")){
                AjReportExp ajReportExp = new AjReportExp();
                ajReportExp.processRce(url, postData, postTxt, responseArea);
            }


        });


        return stackPane;
    }
}
