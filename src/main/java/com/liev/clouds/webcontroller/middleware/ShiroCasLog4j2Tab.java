package com.liev.clouds.webcontroller.middleware;

import com.liev.clouds.interfaces.TabContent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

public class ShiroCasLog4j2Tab implements TabContent {

    @Override
    public StackPane getContent() {
        StackPane stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();

        // 顶部输入区
        HBox topContainer = new HBox(10);
        topContainer.setPadding(new Insets(10));

        TextField keyField = new TextField("ab645dd196197df7");
        Button decryptButton = new Button("解密");
        decryptButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        keyField.setMinHeight(28);
        decryptButton.setMinHeight(28);

        TextField nameField = new TextField("rebeyond");
        Button keyCalcButton = new Button("Key值计算");
        keyCalcButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        nameField.setMinHeight(28);
        keyCalcButton.setMinHeight(28);

        // 留出按钮逻辑代码空白
        decryptButton.setOnAction(event -> {
            // 解密按钮逻辑
        });

        keyCalcButton.setOnAction(event -> {
            // Key值计算按钮逻辑
        });

        topContainer.getChildren().addAll(keyField, decryptButton, nameField, keyCalcButton);

        // 中部显示区
        GridPane centerContainer = new GridPane();
        centerContainer.setPadding(new Insets(10));
        centerContainer.setHgap(10);
        centerContainer.setVgap(10);

        Label requestLabel = new Label("待解密的请求数据包:");
        TextArea requestArea = new TextArea();
        requestArea.setPrefHeight(300);
        requestArea.setPrefWidth(1175);

        Label responseLabel = new Label("解密后的内容:");
        TextArea responseArea = new TextArea();
        responseArea.setPrefHeight(300);
        responseArea.setPrefWidth(1175);

        centerContainer.add(requestLabel, 0, 0);
        centerContainer.add(requestArea, 0, 1);
        centerContainer.add(responseLabel, 0, 2);
        centerContainer.add(responseArea, 0, 3);

        borderPane.setTop(topContainer);
        borderPane.setCenter(centerContainer);
        stackPane.getChildren().add(borderPane);

        return stackPane;
    }
}

