package com.liev.clouds.tabpane.sub;

import com.liev.clouds.webtab.state.WaitTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/7/26 10:35
 */
public class EncryptionDecryptionPane {
    private TabPane tabPane;

    public TabPane getTabPane() {
        return tabPane;
    }

    public EncryptionDecryptionPane(){
        tabPane = new TabPane();

        // 创建每个子Tab及其对应的独立GUI页面
        Tab subTab1 = new Tab("URL", new WaitTab().getContent());
        Tab subTab2 = new Tab("Base64", new WaitTab().getContent());
        Tab subTab3 = new Tab("HTML", new WaitTab().getContent());

        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(subTab1, subTab2, subTab3);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }
}
