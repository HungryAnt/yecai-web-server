package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.model.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ant on 2016/1/9.
 */
public abstract class GoodsBaseCollection {
    private List<Goods> goodsList = new ArrayList<>();

    protected void add(Goods goods) {
        goodsList.add(goods);
    }

    public List<Goods> getGoods() {
        return goodsList;
    }
}
