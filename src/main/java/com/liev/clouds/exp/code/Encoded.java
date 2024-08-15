package com.liev.clouds.exp.code;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @author Revcloud
 * @since 2024/8/15 15:25
 */
public class Encoded {

    /**
     * base64编码
     * @param str
     * @param codeType 编码方式
     * @throws UnsupportedEncodingException
     */
    public static String base64Encoded(String str, String codeType) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes(codeType));
    }

    /**
     * URL编码
     * @param url
     * @param codeType 编码方式
     * @throws UnsupportedEncodingException
     */
    public static String urlEncoded(String url, String codeType) throws UnsupportedEncodingException {
        return URLEncoder.encode(url, codeType);
    }

    /**
     * unicode编码
     * @param str
     * @param codeType
     * @return
     */
    public static String unicodeEncoded(String str, String codeType){
        char[] utfBytes = str.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }
}
