package com.liev.clouds.webcontroller.state;

import com.liev.clouds.interfaces.TabContent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Revcloud
 * @since 2024/7/26 10:42
 */
public class IngTab implements TabContent {
    @Override
    public Pane getContent() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();
        Label label = new Label("工具开发中.....");
        vBox.getChildren().add(label);
        stackPane.getChildren().add(vBox);
        return stackPane;
    }
}
