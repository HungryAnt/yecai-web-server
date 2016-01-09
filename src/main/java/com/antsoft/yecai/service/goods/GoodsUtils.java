package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.mapper.Goods;
import com.antsoft.yecai.type.GoodsType;

/**
 * Created by ant on 2016/1/9.
 */
public class GoodsUtils {
    public static Goods createGoods(GoodsType goodsType, String key, long price) {
        Goods goods = new Goods();
        goods.setGoodsType(goodsType);
        goods.setKey(key);
        goods.setPrice(price);
        return goods;
    }
}
