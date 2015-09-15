package com.antsoft.yecai.model;

import org.joda.time.DateTime;

/**
 * Created by ant on 2015/9/15.
 */
public class User {
    private long id;
    private String userId;
    private String userName;
    private int lv;
    private int exp;
    private String remark;
    private int vip;
    private DateTime mtime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public DateTime getMtime() {
        return mtime;
    }

    public void setMtime(DateTime mtime) {
        this.mtime = mtime;
    }
}
