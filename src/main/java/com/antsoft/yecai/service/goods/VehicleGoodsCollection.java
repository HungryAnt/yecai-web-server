package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.type.EquipmentType;
import org.springframework.stereotype.Service;

/**
 * Created by ant on 2016/1/9.
 */
@Service
public class VehicleGoodsCollection extends VehicleGoodsBaseCollection {
    public VehicleGoodsCollection() {
        initVehicles();
    }

    private void initVehicles() {
        // v0.9.0
        addVehicle(138, 1000);
        addVehicle(144, 1000);
        addVehicle(163, 1000);
        addVehicle(220, 1000);
        addVehicle(228, 1000);
        addVehicle(301, 1000);

        addVehicle(402, 3000);
        addVehicle(450, 4500);
        addVehicle(552, 1000);
        addVehicle(558, 1000);
        addVehicle(569, 1000);

        addVehicle(39, 350);
        addVehicle(40, 350);
        addVehicle(50, 500);
        addVehicle(58, 500);
        addVehicle(59, 500);
        addVehicle(67, 500);
        addVehicle(74, 500);
        addVehicle(75, 500);
        addVehicle(81, 500);
        addVehicle(82, 500);
        addVehicle(83, 350);
        addVehicle(89, 500);
        addVehicle(90, 500);
        addVehicle(91, 500);
        addVehicle(604, 4500);
        addVehicle(828, 3000);
        addVehicle(96, 500);
        addVehicle(97, 500);
        addVehicle(103, 800);
        addVehicle(104, 800);
        addVehicle(108, 500);
        addVehicle(109, 500);
        addVehicle(114, 700);
        addVehicle(115, 350);
        addVehicle(119, 800);
        addVehicle(121, 800);
        addDragon("DragonRed", 99999);
        addDragon("DragonBlack", 99999);
        addDragon("DragonBlue", 99999);
    }

    private void addDragon(String name, long price) {
        String key = String.format("dragon_%s", name);
        add(GoodsUtils.createGoods(EquipmentType.Vehicle, key, price));
    }
}
