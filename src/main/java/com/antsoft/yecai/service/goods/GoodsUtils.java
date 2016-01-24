package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.model.Goods;
import com.antsoft.yecai.type.EquipmentType;

/**
 * Created by ant on 2016/1/9.
 */
public class GoodsUtils {
    public static Goods createGoods(EquipmentType equipmentType, String key, long price) {
        Goods goods = new Goods();
        goods.setEquipmentType(equipmentType);
        goods.setKey(key);
        goods.setPrice(price);
        return goods;
    }
}
