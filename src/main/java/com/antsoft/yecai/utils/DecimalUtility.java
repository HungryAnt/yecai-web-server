package com.antsoft.yecai.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by sunlin05 on 2015/4/15.
 */
public class DecimalUtility {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static BigDecimal toDecimal(double value) {
        return new BigDecimal(DECIMAL_FORMAT.format(value));
    }
}
