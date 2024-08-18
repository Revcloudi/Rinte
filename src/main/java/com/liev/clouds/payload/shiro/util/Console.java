package com.liev.clouds.payload.shiro.util;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;

public class Console extends OutputStream {
    private TextArea console;
    byte[] tempbytes = new byte[1024];

    public Console(TextArea console) {
        this.console = console;
    }

    public void appendText(final String valueOf) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Console.this.console.appendText(valueOf);
            }
        });
    }

    @Override
    public void write(int b) {
        this.appendText(String.valueOf((char)b));
    }
}





