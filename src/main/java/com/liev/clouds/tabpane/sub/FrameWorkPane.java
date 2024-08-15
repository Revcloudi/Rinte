package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webcontroller.TestController;
import com.liev.clouds.webcontroller.framework.AJreportController;
import com.liev.clouds.webcontroller.state.WaitTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class FrameWorkPane {
    private final TabPane tabPane;

    public TabPane getTabPane() {
        return tabPane;
    }

    public FrameWorkPane() {
        tabPane = new TabPane();

        // 创建每个子Tab及其对应的独立GUI页面
        AJreportController ajreportController = new AJreportController();
        Tab subTab1 = new Tab("AJreport", ajreportController.getContent());
        TabConfigUI.setTopLevelTabStyle(subTab1);
        Tab subTab2 = new Tab("Nacos", new TestController().getContent());
        TabConfigUI.setTopLevelTabStyle(subTab2);
        Tab subTab3 = new Tab("RuoYi", new WaitTab().getContent());
        TabConfigUI.setTopLevelTabStyle(subTab3);
        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(subTab1, subTab2, subTab3);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().select(subTab1);
    }
}
