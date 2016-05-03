package com.antsoft.yecai.model;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * Created by ant on 2015/9/15.
 */
@Data
public class User {
    private long id;
    private String userId;
    private String userName;
    private int lv;
    private int exp;
    private String remark;
    private int vip;
    private DateTime mtime;
}
