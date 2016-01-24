package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.type.EquipmentType;

/**
 * Created by ant on 2016/1/9.
 */
public class VehicleGoodsBaseCollection extends GoodsBaseCollection {
    protected void addVehicle(int num, long price) {
        String key = String.format("vehicle_%s", num);
        add(GoodsUtils.createGoods(EquipmentType.Vehicle, key, price));
    }
}
