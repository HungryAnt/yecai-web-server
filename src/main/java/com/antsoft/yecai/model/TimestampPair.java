package com.antsoft.yecai.model;

import lombok.Data;

/**
 * Created by ant on 2015/9/30.
 */
@Data
public class TimestampPair {
    private long clientTimestampInS;
    private long serverTimestampIns;
}
