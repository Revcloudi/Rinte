package com.liev.clouds.webcontroller.encryptiondecryption;

import com.liev.clouds.exp.code.Decode;
import com.liev.clouds.exp.code.Encoded;
import com.liev.clouds.exp.code.Encryption;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Revcloud
 * @since 2024/8/13 15:17
 */
public class EncryptionDecryptionController {

    @FXML
    public ComboBox<String> codeType;

    @FXML
    public ComboBox<String> decodeType;

    @FXML
    public ComboBox<String> hashEncryptionType;

    @FXML
    private ComboBox<String> encodedType;

    @FXML
    private TextArea inputValue;

    @FXML
    private TextArea outputValue;

    private boolean isEncoding = true;

    @FXML
    public void initialize() {
        codeType.getSelectionModel().selectFirst();
        encodedType.getSelectionModel().selectFirst();
        decodeType.getSelectionModel().selectFirst();
        hashEncryptionType.getSelectionModel().selectFirst();

        // 监听编码类型 ComboBox 的选择变化
        encodedType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            isEncoding = true;  // 更新状态为编码
            try {
                processEncoding();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        // 监听解码类型 ComboBox 的选择变化
        decodeType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            isEncoding = false;  // 更新状态为解码
            try {
                processDecode();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        // 监听哈希加密类型 ComboBox 的选择变化
        hashEncryptionType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            try {
                processHashEncryption();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });

        // 监听 TextArea 的文本变化，实现实时编码/解码
        inputValue.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.isEmpty()) {
                    processEncodingOrDecoding();  // 根据状态变量执行编码或解码
                } else {
                    outputValue.clear();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
    }

    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EncryptionDecryption.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane();
        }
    }

    private void processEncodingOrDecoding() throws UnsupportedEncodingException {
        if (isEncoding) {
            processEncoding();
        } else {
            processDecode();
        }
    }

    private void processEncoding() throws UnsupportedEncodingException {
        String encoded = encodedType.getSelectionModel().getSelectedItem();
        String str = inputValue.getText();
        String codeTypes = codeType.getSelectionModel().getSelectedItem();
        if (encoded != null && !str.isEmpty()) {
            switch (encoded) {
                case "Base64编码":
                    outputValue.setText(Encoded.base64Encoded(str, codeTypes));
                    break;
                case "Unicode编码":
                    outputValue.setText(Encoded.unicodeEncoded(str, codeTypes));
                    break;
                case "URL编码":
                    outputValue.setText(Encoded.urlEncoded(str, codeTypes));
                    break;
                case "字符串反转":
                    outputValue.setText(Encoded.stringReversal(str, codeTypes));
                    break;
                case "Ascii码":
                    outputValue.setText(Encoded.toAsciiString(str));
                    break;
                case "转大写":
                    outputValue.setText(Encoded.toUpperCase(str));
                    break;
                case "转小写":
                    outputValue.setText(Encoded.toLowerCase(str));
                    break;
            }
        }
    }

    private void processDecode() throws UnsupportedEncodingException {
        String decode = decodeType.getSelectionModel().getSelectedItem();
        String str = inputValue.getText();
        String codeTypes = codeType.getSelectionModel().getSelectedItem();
        if (decode != null && !str.isEmpty()) {
            switch (decode) {
                case "Base64解码":
                    outputValue.setText(Decode.base64Decode(str, codeTypes));
                    break;
                case "16进制Hex解码":
                    outputValue.setText(Decode.hexadecimalDecode(str, codeTypes));
                    break;
                case "URL解码":
                    outputValue.setText(Decode.urlDecode(str, codeTypes));
                    break;
                case "Unicode解码":
                    outputValue.setText(Decode.unicodeDecode(str, codeTypes));
                    break;
                case "HTML实体解码":
                    outputValue.setText(Decode.decodeHtmlEntities(str));
                    break;
            }
        }
    }

    /**
     * 哈希加密
     * @throws NoSuchAlgorithmException
     */
    private void processHashEncryption() throws NoSuchAlgorithmException {
        String hashType = hashEncryptionType.getSelectionModel().getSelectedItem();
        String str = inputValue.getText();
        String codeTypes = codeType.getSelectionModel().getSelectedItem();
        if (hashType != null && !str.isEmpty()) {
            switch (hashType) {
                case "MD5(16位)":
                    outputValue.setText(Encryption.md5_16bit(str, codeTypes));
                    break;
                case "MD5(32位)":
                    outputValue.setText(Encryption.md5_32bit(str, codeTypes));
                    break;
                case "SHA1":
                    outputValue.setText(Encryption.sha1(str, codeTypes));
                    break;
                case "SHA1(Base64)":
                    outputValue.setText(Encryption.sha1Base64(str, codeTypes));
                    break;
                case "SHA256":
                    outputValue.setText(Encryption.sha256(str, codeTypes));
                    break;
                case "SHA256(Base64)":
                    outputValue.setText(Encryption.sha256Base64(str, codeTypes));
                    break;
                case "SHA384":
                    outputValue.setText(Encryption.sha384(str, codeTypes));
                    break;
                case "SHA384(Base64)":
                    outputValue.setText(Encryption.sha384Base64(str, codeTypes));
                    break;
                case "SHA512":
                    outputValue.setText(Encryption.sha512(str, codeTypes));
                    break;
                case "SHA512(Base64)":
                    outputValue.setText(Encryption.sha512Base64(str, codeTypes));
                    break;
            }
        }
    }
}
