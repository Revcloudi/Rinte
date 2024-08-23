package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webcontroller.middleware.ShiroController;
import com.liev.clouds.webcontroller.state.WaitTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/7/26 9:57
 */
public class MiddleWarePane {

    private final TabPane tabPane;

    public MiddleWarePane(){
        tabPane = new TabPane();

        // 创建每个子Tab及其对应的独立GUI页面
        Tab shiroTab = new Tab("Shiro", new ShiroController().getContent());
        Tab fastJsonTab = new Tab("FastJson", new WaitTab().getContent());
        Tab log4jTab = new Tab("Log4j", new WaitTab().getContent());
        TabConfigUI.setTopLevelTabStyle(shiroTab);
        TabConfigUI.setTopLevelTabStyle(fastJsonTab);
        TabConfigUI.setTopLevelTabStyle(log4jTab);

        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(shiroTab, fastJsonTab, log4jTab);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
