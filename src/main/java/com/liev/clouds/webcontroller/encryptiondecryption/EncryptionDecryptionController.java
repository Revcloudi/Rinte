package com.liev.clouds.webcontroller.encryptiondecryption;

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
    public ComboBox decodeType;

    @FXML
    public ComboBox hashEncryptionType;

    @FXML
    private ComboBox encodedType;

    @FXML
    private TextArea inputValue;

    @FXML
    private TextArea outputValue;


    @FXML
    public void initialize(){
        codeType.getSelectionModel().selectFirst();

        encodedType.getSelectionModel().selectFirst();

        decodeType.getSelectionModel().selectFirst();

        hashEncryptionType.getSelectionModel().selectFirst();

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
