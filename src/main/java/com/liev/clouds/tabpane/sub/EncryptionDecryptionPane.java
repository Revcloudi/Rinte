package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webcontroller.encryptiondecryption.EncryptionDecryptionController;
import com.liev.clouds.webcontroller.state.WaitTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/7/26 10:35
 */
public class EncryptionDecryptionPane {
    private final TabPane tabPane;

    public TabPane getTabPane() {
        return tabPane;
    }

    public EncryptionDecryptionPane(){
        tabPane = new TabPane();

        // 创建每个子Tab及其对应的独立GUI页面
        Tab textDecodeEncoded = new Tab("文本编码/解码", new EncryptionDecryptionController().getContent());
        TabConfigUI.setTopLevelTabStyle(textDecodeEncoded);
        Tab fileDecodeEncoded = new Tab("文件编码/解码", new WaitTab().getContent());
        TabConfigUI.setTopLevelTabStyle(fileDecodeEncoded);
        Tab textFormatting = new Tab("文本格式化", new WaitTab().getContent());
        TabConfigUI.setTopLevelTabStyle(textFormatting);

        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(textDecodeEncoded, fileDecodeEncoded, textFormatting);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }
}
