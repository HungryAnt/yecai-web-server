package com.antsoft.yecai.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by ant on 2015/11/8.
 */
public class MD5SignatureUtil {
    public static String getSignAsHex(String rowText) {
        return DigestUtils.md5DigestAsHex(getBytes(rowText));
    }

    private static byte[] getBytes(String text) {
        try {
            return text.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
