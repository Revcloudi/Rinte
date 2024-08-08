package com.liev.clouds.config;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author Revcloud
 * @since 2024/8/8 17:41
 */
public class ProxyConfig {
    private Proxy proxy;
    private String proxyHost;
    private int proxyPort;
    private String proxyUser;
    private String proxyPassword;
    private String proxyType;

    public ProxyConfig() {
        this.proxy = Proxy.NO_PROXY;
    }

    public Proxy getProxy() {
        return proxy;
    }

    /**
     * 设置代理配置。
     *
     * @param host 代理主机地址
     * @param port 代理端口
     * @param type 代理类型（HTTP 或 SOCKS）
     * @param user 代理用户名
     * @param password 代理密码
     */
    public void setProxy(String host, int port, String type, String user, String password) {
        this.proxyHost = host;
        this.proxyPort = port;
        this.proxyUser = user;
        this.proxyPassword = password;
        this.proxyType = type;

        if ("HTTP".equalsIgnoreCase(type)) {
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        } else if ("SOCKS".equalsIgnoreCase(type)) {
            this.proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port));
        } else {
            this.proxy = Proxy.NO_PROXY;
        }
    }

    /**
     * 清除代理配置。
     */
    public void clearProxy() {
        this.proxy = Proxy.NO_PROXY;
        this.proxyHost = null;
        this.proxyPort = 0;
        this.proxyUser = null;
        this.proxyPassword = null;
        this.proxyType = null;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public String getProxyType() {
        return proxyType;
    }
}
