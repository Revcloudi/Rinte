package com.liev.clouds.webcontroller.shell;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * @author Revcloud
 * @since 2024/8/15 15:40
 */
public class MemoryShellController {



    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MemoryShell.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane();
        }
    }

}
