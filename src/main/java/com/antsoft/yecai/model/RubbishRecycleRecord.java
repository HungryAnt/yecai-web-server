package com.antsoft.yecai.model;

/**
 * Created by ant on 2015/10/1.
 */
public class RubbishRecycleRecord {
    private String userId;
    private int rubbishCount;
    private long amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRubbishCount() {
        return rubbishCount;
    }

    public void setRubbishCount(int rubbishCount) {
        this.rubbishCount = rubbishCount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
