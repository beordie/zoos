package com.beordie.util;

import java.nio.charset.StandardCharsets;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/7 16:40
 * @User beordie
 */
public class StringUtil {
    public static String encrypt(String code) {
        final byte[] bytes = code.getBytes(StandardCharsets.UTF_8);
        String encode = "", temp = "";
        for (int n = 0; n < bytes.length; n++) {
            temp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
            if (encode.length() == 1) {
                encode = encode + "0" + temp;
            } else {
                encode = encode + temp;
            }
        }
        return encode;
    }
}
