package com.liev.clouds.webcontroller.framework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * @author Revcloud
 * @since 2024/8/31 14:58
 */
public class NacosController {

    @FXML
    public TextField url;

    @FXML
    public ComboBox<String> options;

    @FXML
    public TextField requestHeader;

    @FXML
    public TextField requestBody;

    @FXML
    public TextArea log;

    @FXML
    public TextArea responseBody;

    @FXML
    public void initialize(){
        options.getSelectionModel().selectFirst();
    }

    @FXML
    public void check(ActionEvent actionEvent) {

    }

    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Nacos.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane();
        }
    }
}
