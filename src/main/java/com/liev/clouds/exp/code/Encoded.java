package com.liev.clouds.exp.code;

import java.io.UnsupportedEncodingException;
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

    /**
     * 字符串反转
     * @param str
     * @param codeType
     * @return
     */
    public static String stringReversal(String str, String codeType){
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder reversedStr = new StringBuilder(str);
        return reversedStr.reverse().toString();
    }

    /**
     * 字符串转大写
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * 字符串转小写
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * Ascii编码
     *
     * @param str
     * @return
     */
    public static String toAsciiString(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder asciiString = new StringBuilder();

        // 将字符串中的每个字符转换为相应的ASCII值，并用空格分隔
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = (int) str.charAt(i);
            asciiString.append(asciiValue);

            // 在每个ASCII值后面加一个空格，最后一个字符不加
            if (i < str.length() - 1) {
                asciiString.append(" ");
            }
        }

        return asciiString.toString();
    }

}
