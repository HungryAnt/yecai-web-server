package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.type.EquipmentType;

/**
 * Created by ant on 2016/1/9.
 */
public class NostalgicVehicleGoodsCollection extends GoodsBaseCollection {
    public NostalgicVehicleGoodsCollection() {
        initNostalgicVehicles();
    }

    private void initNostalgicVehicles() {
        addNostalgicVehicle(10, 1000);
        addNostalgicVehicle(24, 1000);
        addNostalgicVehicle(26, 400);
        addNostalgicVehicle(35, 550);
        addNostalgicVehicle(39, 550);
        addNostalgicVehicle(41, 550);
        addNostalgicVehicle(43, 550);
        addNostalgicVehicle(69, 2000);
        addNostalgicVehicle(119, 2000);
    }

    private void addNostalgicVehicle(int num, long price) {
        String key = String.format("vehicle2_%s", num);
        add(GoodsUtils.createGoods(EquipmentType.Vehicle, key, price));
    }
}
