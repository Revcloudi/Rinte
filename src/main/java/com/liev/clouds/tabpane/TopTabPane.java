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
        Tab cmsTab = new Tab("各类CMS");
        TabConfigUI.setTopLevelTabStyle(cmsTab);
        FrameWorkPane frameWorkPane = new FrameWorkPane();
        cmsTab.setContent(frameWorkPane.getTabPane());

        // 创建第二个Tab和第二排TabPane
        Tab javaMiddlewareTab = new Tab("Java中间件");
        TabConfigUI.setTopLevelTabStyle(javaMiddlewareTab);
        MiddleWarePane middleWareTab = new MiddleWarePane();
        javaMiddlewareTab.setContent(middleWareTab.getTabPane());

        // 继续为其他顶层Tab创建对应的第二排TabPane...
        Tab decodeEncodedTab = new Tab("编码/解码");
        TabConfigUI.setTopLevelTabStyle(decodeEncodedTab);
        EncryptionDecryptionPane encryptionDecryptionPane = new EncryptionDecryptionPane();
        decodeEncodedTab.setContent(encryptionDecryptionPane.getTabPane());

        Tab shellCreateTab = new Tab("Shell生成");
        TabConfigUI.setTopLevelTabStyle(shellCreateTab);
        ShellGeneratePane shellGeneratePane = new ShellGeneratePane();
        shellCreateTab.setContent(shellGeneratePane.getTabPane());

        Tab settingTab = new Tab("设置");
        TabConfigUI.setTopLevelTabStyle(settingTab);
        SettingPane settingPane = new SettingPane();
        settingTab.setContent(settingPane.getTabPane());

        // 将所有顶层Tab添加到顶层TabPane中
        tabPane.getTabs().addAll(cmsTab, javaMiddlewareTab, decodeEncodedTab, shellCreateTab, settingTab);

        // 设置TabPane样式
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
