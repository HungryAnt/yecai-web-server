package com.antsoft.yecai.model;

/**
 * Created by ant on 2015/10/1.
 */
public class GoodsPaymentRecord {
    private String userId;
    private String goodsKey;
    private long amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsKey() {
        return goodsKey;
    }

    public void setGoodsKey(String goodsKey) {
        this.goodsKey = goodsKey;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
