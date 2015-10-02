package com.antsoft.yecai.model;

/**
 * Created by ant on 2015/10/2.
 */
public class RechargeRecord {
    private String userId;
    private long amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
