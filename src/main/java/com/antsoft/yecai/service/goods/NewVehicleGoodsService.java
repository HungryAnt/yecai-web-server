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
        addVehicle(322, 1000);
        addVehicle(326, 1000);
        addVehicle(327, 1000);
        addVehicle(331, 1000);
        addVehicle(332, 1000);
        addVehicle(336, 1000);
        addVehicle(340, 1000);
        addVehicle(345, 1000);
        addVehicle(346, 1000);
        addVehicle(354, 1000);
        addVehicle(359, 1000);
        addVehicle(521, 4500); // 重型坦克
        addVehicle(662, 1000); // 天启坦克
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
        addVehicle(514, 1000);
        addVehicle(574, 1000);
        addVehicle(598, 1000);
        addVehicle(708, 1000);
        addVehicle(723, 1000);

        // 时装载具
        addVehicle(697, 1000);
        addVehicle(376, 1000);
        addVehicle(456, 1000);
        addVehicle(439, 1000);

        // 飞碟
        addVehicle(192, 1000);
        addVehicle(461, 1000);
    }
}
