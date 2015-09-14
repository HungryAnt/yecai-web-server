/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sunlin05 on 2015/2/19.
 */
public final class Sha1Utility {
    public static String sha1(String raw) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(raw.getBytes());
            return bytesToHexStr(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("sha1 encryption error", e);
        }
    }

    private static String bytesToHexStr(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(byteToHexStr(b));
        }
        return stringBuilder.toString();
    }

    private static String byteToHexStr(byte b) {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = digit[(b >>> 4) & 0x0F];
        tempArr[1] = digit[b & 0x0F];
        return new String(tempArr);
    }
}
