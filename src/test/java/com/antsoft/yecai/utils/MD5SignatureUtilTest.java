package com.antsoft.yecai.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ant on 2015/11/8.
 */
public class MD5SignatureUtilTest {
    @Test
    public void testGetSignAsHex() {
        String expectedSign = "71736cfd60d4523017cc23ae12231c13";
        String actual = MD5SignatureUtil.getSignAsHex("ant111AntLogin");
        assertEquals(expectedSign, actual);
    }
}
