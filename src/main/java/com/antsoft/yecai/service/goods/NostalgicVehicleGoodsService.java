package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.type.GoodsType;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2016/1/9.
 */
@Service
public class NostalgicVehicleGoodsService extends GoodsServiceBase {
    public NostalgicVehicleGoodsService() {
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
        add(GoodsUtils.createGoods(GoodsType.Vehicle, key, price));
    }
}
