package com.liev.clouds.tabpane;
import com.liev.clouds.config.TabConfigUI;
import com.liev.clouds.tabpane.sub.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class TopTabPane {

    private final TabPane tabPane;


    public TopTabPane() {
        tabPane = new TabPane();

        // 创建第一个Tab和第二排TabPane
        Tab tab1 = new Tab("框架通用漏洞检测");
        TabConfigUI.setTopLevelTabStyle(tab1);
        FrameWorkPane frameWorkPane = new FrameWorkPane();
        tab1.setContent(frameWorkPane.getTabPane());

        // 创建第二个Tab和第二排TabPane
        Tab tab2 = new Tab("中间件漏洞检测");
        TabConfigUI.setTopLevelTabStyle(tab2);
        MiddleWarePane middleWareTab = new MiddleWarePane();
        tab2.setContent(middleWareTab.getTabPane());

        // 继续为其他顶层Tab创建对应的第二排TabPane...
        Tab tab3 = new Tab("编码/解码");
        TabConfigUI.setTopLevelTabStyle(tab3);
        EncryptionDecryptionPane encryptionDecryptionPane = new EncryptionDecryptionPane();
        tab3.setContent(encryptionDecryptionPane.getTabPane());

        Tab tab4 = new Tab("Shell生成");
        TabConfigUI.setTopLevelTabStyle(tab4);
        ShellGeneratePane shellGeneratePane = new ShellGeneratePane();
        tab4.setContent(shellGeneratePane.getTabPane());

        Tab settingTab = new Tab("设置");
        TabConfigUI.setTopLevelTabStyle(settingTab);
        SettingPane settingPane = new SettingPane();
        settingTab.setContent(settingPane.getTabPane());

        // 将所有顶层Tab添加到顶层TabPane中
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, settingTab);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
