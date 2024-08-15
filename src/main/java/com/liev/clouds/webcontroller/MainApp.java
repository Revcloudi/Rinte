package com.liev.clouds.webcontroller;

import com.liev.clouds.tabpane.TopTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Revcloudi的小工具");

        // 创建顶层TabPane
        TopTabPane topTabPane = new TopTabPane();

        VBox root = new VBox(topTabPane.getTabPane());

        // 设置场景并显示
        Scene scene = new Scene(root, 1200, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
