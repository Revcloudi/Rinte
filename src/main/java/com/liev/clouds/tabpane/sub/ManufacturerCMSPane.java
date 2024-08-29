package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webcontroller.state.WaitTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/8/25 14:24
 */
public class ManufacturerCMSPane {
    private final TabPane tabPane;

    public TabPane getTabPane() {
        return tabPane;
    }

    public ManufacturerCMSPane(){
        tabPane = new TabPane();

        Tab ruanFanTab = new Tab("软帆cms", new WaitTab().getContent());
        TabConfigUI.setTopLevelTabStyle(ruanFanTab);

        tabPane.getTabs().addAll(ruanFanTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }
}
