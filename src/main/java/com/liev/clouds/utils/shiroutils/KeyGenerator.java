package com.liev.clouds.utils.shiroutils;

import org.apache.shiro.codec.Base64;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * 随机生成shiro key
 */
public class KeyGenerator {
    public static void main(String[] args) {
      KeyGenerator keyGenerator = new KeyGenerator();
        System.out.println(keyGenerator.getKey());

    }

    public String getKey() {
        javax.crypto.KeyGenerator keygen = null;
        try {
            keygen = javax.crypto.KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey deskey = keygen.generateKey();
        return Base64.encodeToString(deskey.getEncoded());
    }
}
