package com.liev.clouds.tabpane.sub;

import com.liev.clouds.webtab.framework.AJreportController;
import com.liev.clouds.webtab.state.WaitTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/7/26 10:28
 */
public class FrameWorkPane {
    private TabPane tabPane;

    public TabPane getTabPane() {
        return tabPane;
    }

    public FrameWorkPane() {
        tabPane = new TabPane();

        // 创建每个子Tab及其对应的独立GUI页面
        AJreportController ajreportController = new AJreportController();
        Tab subTab1 = new Tab("AJreport", ajreportController.getContent());
        Tab subTab2 = new Tab("Nacos", new WaitTab().getContent());
        Tab subTab3 = new Tab("RuoYi", new WaitTab().getContent());

        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(subTab1, subTab2, subTab3);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabPane.getSelectionModel().select(subTab1);
    }
}
