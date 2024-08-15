package com.liev.clouds.webcontroller.framework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * @author Revcloud
 * @since 2024/8/13 15:17
 */
public class EncryptionDecryptionController {
    @FXML
    public ComboBox codeType;

    @FXML
    public ComboBox decodingType;

    @FXML
    public ComboBox encryptionType;

    @FXML
    private ComboBox encodingType;

    @FXML
    private TextArea input;

    @FXML
    private TextArea output;


    @FXML
    public void initialize(){
        codeType.getSelectionModel().selectFirst();

        encodingType.getSelectionModel().selectFirst();

        decodingType.getSelectionModel().selectFirst();

        encryptionType.getSelectionModel().selectFirst();

        //TODO 按钮逻辑
    }


    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EncryptionDecryption.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(); // Return an empty pane if loading fails
        }
    }
}
