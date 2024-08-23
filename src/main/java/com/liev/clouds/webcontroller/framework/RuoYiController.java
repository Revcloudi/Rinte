package com.liev.clouds.webcontroller.framework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * @author Revcloud
 * @since 2024/7/30 17:36
 */
public class RuoYiController {

    @FXML
    public ComboBox options;

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
}
