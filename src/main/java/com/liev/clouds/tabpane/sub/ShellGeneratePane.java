package com.liev.clouds.tabpane.sub;

import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.webcontroller.shell.MemoryShellController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/8/15 15:39
 */
public class ShellGeneratePane {
    private final TabPane tabPane;

    public TabPane getTabPane(){
        return  tabPane;
    }

    public ShellGeneratePane(){
        tabPane = new TabPane();

        MemoryShellController memoryShellController = new MemoryShellController();

        // 创建每个子Tab及其对应的独立GUI页面
        Tab javaShell = new Tab("Java内存马生成", memoryShellController.getContent());

        TabConfigUI.setTopLevelTabStyle(javaShell);

        // 添加子Tab到子TabPane
        tabPane.getTabs().addAll(javaShell);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabPane.getSelectionModel().select(javaShell);
    }
}
