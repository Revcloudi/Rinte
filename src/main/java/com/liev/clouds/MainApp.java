package com.liev.clouds;

import com.liev.clouds.tabpane.TopTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("工具分析软件");

        // 创建顶层TabPane
        TopTabPane topTabPane = new TopTabPane();

        // 设置场景并显示
        Scene scene = new Scene(new VBox(topTabPane.getTabPane()), 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
