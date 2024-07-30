package com.liev.clouds.dao;

import javafx.scene.control.TextArea;

import java.util.List;

/**
 * @author Revcloud
 * @since 2024/7/30 15:42
 */
public class AjReportDetectionParams {

    private String url;
    private List<String> postDataList;
    private TextArea postTxt;
    private TextArea outComeArea;
    private TextArea responseArea;

    // Getters and Setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public List<String> getPostDataList() { return postDataList; }
    public void setPostDataList(List<String> postDataList) { this.postDataList = postDataList; }

    public TextArea getPostTxt() { return postTxt; }
    public void setPostTxt(TextArea postTxt) { this.postTxt = postTxt; }

    public TextArea getOutComeArea() { return outComeArea; }
    public void setOutComeArea(TextArea outComeArea) { this.outComeArea = outComeArea; }

    public TextArea getResponseArea() { return responseArea; }
    public void setResponseArea(TextArea responseArea) { this.responseArea = responseArea; }

    public AjReportDetectionParams(String url, List<String> postDataList, TextArea postTxt, TextArea outComeArea, TextArea responseArea) {
        this.url = url;
        this.postDataList = postDataList;
        this.postTxt = postTxt;
        this.outComeArea = outComeArea;
        this.responseArea = responseArea;
    }
}
