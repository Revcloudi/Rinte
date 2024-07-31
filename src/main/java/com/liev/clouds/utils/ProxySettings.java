package com.liev.clouds.utils;

public class ProxySettings {
    private static String proxyHost;
    private static String proxyPort;
    private static String proxyUser;
    private static String proxyPassword;
    private static boolean useProxy = false;

    public static void setProxy(String host, String port, String user, String password) {
        proxyHost = host;
        proxyPort = port;
        proxyUser = user;
        proxyPassword = password;
        useProxy = true;
    }

    public static void disableProxy() {
        useProxy = false;
    }

    public static void applyProxySettings() {
        if (useProxy) {
            System.setProperty("http.proxyHost", proxyHost);
            System.setProperty("http.proxyPort", proxyPort);
            System.setProperty("https.proxyHost", proxyHost);
            System.setProperty("https.proxyPort", proxyPort);
            if (proxyUser != null && !proxyUser.isEmpty()) {
                System.setProperty("http.proxyUser", proxyUser);
                System.setProperty("http.proxyPassword", proxyPassword);
            }
        } else {
            System.clearProperty("http.proxyHost");
            System.clearProperty("http.proxyPort");
            System.clearProperty("https.proxyHost");
            System.clearProperty("https.proxyPort");
            System.clearProperty("http.proxyUser");
            System.clearProperty("http.proxyPassword");
        }
    }

    public static String getProxyHost() {
        return proxyHost;
    }

    public static String getProxyPort() {
        return proxyPort;
    }

    public static String getProxyUser() {
        return proxyUser;
    }

    public static String getProxyPassword() {
        return proxyPassword;
    }
}
