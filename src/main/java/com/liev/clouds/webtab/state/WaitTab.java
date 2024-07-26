package com.liev.clouds.webtab.state;

import com.liev.clouds.interfaces.TabContent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Revcloud
 * @since 2024/7/26 10:37
 */
public class WaitTab implements TabContent {
    @Override
    public Pane getContent() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();
        Label label = new Label("工具待开发");
        vBox.getChildren().add(label);
        stackPane.getChildren().add(vBox);
        return stackPane;
    }
}
