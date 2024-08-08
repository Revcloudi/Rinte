package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webtab.top.ProxySettingsController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/7/31 10:01
 */
public class SettingPane {

    private TabPane tabPane;

    public TabPane getTabPane(){
        return  tabPane;
    }

    public SettingPane(){
        tabPane = new TabPane();

        ProxySettingsController proxySettingsController = new ProxySettingsController();

        // 创建每个子Tab及其对应的独立GUI页面
        Tab proxyTab = new Tab("代理", proxySettingsController.getContent());

        TabConfigUI.setTopLevelTabStyle(proxyTab);

        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(proxyTab);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabPane.getSelectionModel().select(proxyTab);
    }
}
