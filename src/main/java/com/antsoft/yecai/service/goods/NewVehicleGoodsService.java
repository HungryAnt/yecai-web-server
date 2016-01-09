package com.antsoft.yecai.service.goods;

import org.springframework.stereotype.Service;

/**
 * Created by ant on 2016/1/9.
 */
@Service
public class NewVehicleGoodsService extends VehicleGoodsServiceBase {
    public NewVehicleGoodsService() {
        initVehicles();
    }

    private void initVehicles() {
        // v1.0.0
        addVehicle(321, 1000);
        addVehicle(322, 2000);
        addVehicle(326, 1000);
        addVehicle(327, 4500);
        addVehicle(331, 1000);
        addVehicle(332, 1500);
        addVehicle(336, 1000);
        addVehicle(340, 2000);
        addVehicle(345, 1000);
        addVehicle(346, 1000);
        addVehicle(354, 2000);
        addVehicle(359, 1800);
        addVehicle(521, 2000); // 重型坦克
        addVehicle(662, 4500); // 天启坦克
        addVehicle(802, 4500);
        addVehicle(381, 1000);

        // 圣诞车辆
        addVehicle(168, 1000);
        addVehicle(172, 1000);
        addVehicle(178, 1000);
        addVehicle(188, 1000);
        addVehicle(222, 1000);
        addVehicle(710, 1000);
        addVehicle(711, 1000);

        // 披风
        addVehicle(433, 1000);
        addVehicle(497, 1000);
        addVehicle(514, 4500);
        addVehicle(574, 4500);
        addVehicle(598, 4500);
        addVehicle(708, 4500);
        addVehicle(723, 1000);

        // 时装载具
        addVehicle(697, 45);
        addVehicle(376, 1000);
        addVehicle(456, 1000);
        addVehicle(439, 45);

        // 飞碟
        addVehicle(192, 2000);
        addVehicle(461, 2000);
    }
}
