package com.liev.clouds.config;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Revcloud
 * @since 2024/8/8 14:42
 */
public class TabConfigUI {
    // 常量定义，去掉标签边框的样式
    private static final String TAB_STYLE =
            "-fx-font-size: 14px; " +
                    "-fx-min-height: 30px; " +
                    "-fx-pref-height: 30px; " +
                    "-fx-max-height: 30px; " +
                    "-fx-border-width: 0; " +      // 设置边框宽度为0
                    "-fx-border-color: transparent;"; // 设置边框颜色为透明

    /**
     * 设置顶级标签的样式
     * @param tab 要设置样式的Tab对象
     * @throws IllegalArgumentException 如果tab为null
     */
    public static void setTopLevelTabStyle(Tab tab) {
        if (tab == null) {
            throw new IllegalArgumentException("Tab cannot be null");
        }
        tab.setStyle(TAB_STYLE);
    }
}
