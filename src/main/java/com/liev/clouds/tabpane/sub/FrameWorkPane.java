package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webcontroller.setting.ProxySettingsController;
import com.liev.clouds.webcontroller.framework.AJreportController;
import com.liev.clouds.webcontroller.framework.RuoYiController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class FrameWorkPane {
    private final TabPane tabPane;

    public TabPane getTabPane() {
        return tabPane;
    }

    public FrameWorkPane() {
        tabPane = new TabPane();

        AJreportController ajreportController = new AJreportController();
        Tab AjreportTab = new Tab("AJreport", ajreportController.getContent());
        TabConfigUI.setTopLevelTabStyle(AjreportTab);
        Tab nacosTab = new Tab("Nacos", new ProxySettingsController().getContent());
        TabConfigUI.setTopLevelTabStyle(nacosTab);
        Tab ruoyiTab = new Tab("RuoYi", new RuoYiController().getContent());
        TabConfigUI.setTopLevelTabStyle(ruoyiTab);

        tabPane.getTabs().addAll(AjreportTab, nacosTab, ruoyiTab);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getSelectionModel().select(AjreportTab);
    }
}
