package com.liev.clouds.exp.code;

import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

/**
 * @author Revcloud
 * @since 2024/8/15 15:25
 */
public class Decode {

    /**
     * 16进制（Hex）解码
     * @param hexStr 编码字符串
     * @param codeType 编码方式
     */
    public static String hexadecimalDecode(String hexStr, String codeType){
        // 将十六进制字符串转换为字节数组
        int length = hexStr.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexStr.charAt(i), 16) << 4)
                    + Character.digit(hexStr.charAt(i+1), 16));
        }
        // 将字节数组转换为字符串
        return new String(bytes);
    }

    /**
     * base64解码
     * @param str
     * @param codeType 编码方式
     * @throws UnsupportedEncodingException
     */
    public static String base64Decode(String str, String codeType) throws UnsupportedEncodingException {
        byte[] base64decodedBytes =  Base64.getDecoder().decode(str);
        return new String(base64decodedBytes, codeType);
    }

    /**
     * URL解码
     * @param url
     * @param codeType 编码方式
     * @throws UnsupportedEncodingException
     */
    public static String urlDecode(String url, String codeType) throws UnsupportedEncodingException {
        return URLDecoder.decode(url, codeType);
    }


    /**
     * unicode解码
     * @param str
     * @param codeType
     * @return
     */
    public static String unicodeDecode(String str ,String codeType){
        int start = 0;
        int end = 0;
        StringBuilder buffer = new StringBuilder();
        while (start > -1) {
            end = str.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = str.substring(start + 2, str.length());
            } else {
                charStr = str.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(Character.toString(letter));
            start = end;
        }
        return buffer.toString();
    }

    /**
     * HTML实体解码
     * @param str
     * @return
     */
    public static String decodeHtmlEntities(String str) {
        if (str == null) {
            return null;
        }
        return StringEscapeUtils.unescapeHtml4(str);
    }

}
