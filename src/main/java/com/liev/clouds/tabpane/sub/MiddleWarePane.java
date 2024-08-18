package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webcontroller.middleware.MainController;
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
        Tab subTab1 = new Tab("Shiro", new MainController().getContent());
        Tab subTab2 = new Tab("FastJson", new WaitTab().getContent());
        Tab subTab3 = new Tab("Log4j", new WaitTab().getContent());
        TabConfigUI.setTopLevelTabStyle(subTab1);
        TabConfigUI.setTopLevelTabStyle(subTab2);
        TabConfigUI.setTopLevelTabStyle(subTab3);

        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(subTab1, subTab2, subTab3);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
