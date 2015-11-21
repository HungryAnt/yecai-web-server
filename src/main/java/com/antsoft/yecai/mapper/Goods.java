package com.antsoft.yecai.mapper;

import com.antsoft.yecai.type.GoodsType;

/**
 * Created by ant on 2015/9/22.
 */
public class Goods {
    private GoodsType goodsType;
    private String key;
    private String name;
    private long price;

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
