package com.liev.clouds.exp.code;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密
 * @author Revcloud
 * @since 2024/8/15 15:27
 */
public class Encryption {
    // MD5(16位) 加密
    public static String md5_16bit(String str) throws NoSuchAlgorithmException {
        return md5_32bit(str).substring(8, 24);
    }

    // MD5(32位) 加密
    public static String md5_32bit(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }

    // SHA1 加密
    public static String sha1(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }

    // SHA1(Base64) 加密
    public static String sha1Base64(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digest);
    }

    // SHA256 加密
    public static String sha256(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }

    // SHA256(Base64) 加密
    public static String sha256Base64(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digest);
    }

    // SHA384 加密
    public static String sha384(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }

    // SHA384(Base64) 加密
    public static String sha384Base64(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digest);
    }

    // SHA512 加密
    public static String sha512(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }

    // SHA512(Base64) 加密
    public static String sha512Base64(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digest);
    }

    // 将字节数组转换为十六进制字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
