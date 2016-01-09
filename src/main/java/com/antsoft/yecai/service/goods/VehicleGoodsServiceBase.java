package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.type.GoodsType;

/**
 * Created by ant on 2016/1/9.
 */
public class VehicleGoodsServiceBase extends GoodsServiceBase {
    protected void addVehicle(int num, long price) {
        String key = String.format("vehicle_%s", num);
        add(GoodsUtils.createGoods(GoodsType.Vehicle, key, price));
    }
}
