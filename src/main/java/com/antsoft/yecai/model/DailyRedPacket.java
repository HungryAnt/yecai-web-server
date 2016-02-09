package com.antsoft.yecai.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

/**
 * Created by ant on 2016/2/9.
 */
@Data
@EqualsAndHashCode
public class DailyRedPacket {
    private long id;
    private String userId;
    private long amount;
    private DateTime ctime;
}
