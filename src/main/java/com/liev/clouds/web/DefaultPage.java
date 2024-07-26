package com.liev.clouds.web;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DefaultPage {

    private BorderPane page;

    public DefaultPage() {
        page = new BorderPane();

        TextField urlField = new TextField("http://127.0.0.1:8080/");
        Button queryButton = new Button("检测");
        queryButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // 留出按钮逻辑代码空白
        queryButton.setOnAction(event -> {
            // 这里可以添加按钮点击逻辑
        });

        HBox topContainer = new HBox(10, urlField, queryButton);
        topContainer.setPadding(new Insets(10));
        page.setTop(topContainer);

        StackPane centerPane = new StackPane();
        centerPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
        // 根据你的需求添加中心面板内容
        page.setCenter(centerPane);
    }

    public BorderPane getPage() {
        return page;
    }
}
