package com.liev.clouds.exp;

import javafx.scene.control.TextArea;

/**
 * @author Revcloud
 * @since 2024/7/26 15:07
 */
public class AjReportExp {

    public void processDetection(String url, String postData, TextArea postTxt, TextArea responseArea) {
        // 这里可以添加实际的检测逻辑，当前仅为示例逻辑
        String result = url + "存在漏洞！";

        //TODO 调用所有方法



        // 将结果显示在responseArea上
        postTxt.setText(postData);
        responseArea.setText(result);
    }

    /**
     * CVE-2024-5352(任意命令执行)
     * @param url
     * @param postData
     * @param postTxt
     * @param responseArea
     */
    public void processRce(String url, String postData, TextArea postTxt, TextArea responseArea){
        //TODO

        boolean aj = true;
        String result = aj ? "漏洞存在！" : "未检测到漏洞";

        postTxt.setText(postData);
        responseArea.setText(result);

    }

}
