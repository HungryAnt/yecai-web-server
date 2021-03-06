package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.model.Goods;
import com.antsoft.yecai.type.EquipmentType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ant on 2016/1/9.
 */
public class EquipmentGoodsCollection extends GoodsBaseCollection {
    private List<Goods> wings = new ArrayList<>();
    private List<Goods> hats = new ArrayList<>();
    private List<Goods> eyeWears = new ArrayList<>();

    public EquipmentGoodsCollection() {
        initWings();
        initHats();
        initEyeWears();
    }

    public List<Goods> getWings() {
        return wings;
    }

    public List<Goods> getEyeWears() {
        return eyeWears;
    }

    public List<Goods> getHats() {
        return hats;
    }

    private void initWings() {
        addWing(16, 1000);
        addWing(48, 1000);
        addWing(51, 500);
        addWing(52, 1000);
        addWing(94, 1000);
        addWing(130, 2400);
        addWing(131, 2400);
        addWing(253, 1000);
        addWing(278, 2400);
        addWing(316, 2400);
        addWing(338, 2400);
        addWing(355, 1000);
        addWing(394, 2400);
        addWing(430, 2400);
        addWing(467, 2400);
        addWing(486, 1000);
        addWing(559, 1000);
        addWing(587, 1000);
        addWing(617, 2400);
        addWing(686, 2400);
        addWing(715, 1000);
        addWing(772, 2400);
        addWing(795, 2400);
        addWing(819, 2400);

        addWing2(13, 1000);
        addWing2(15, 500);
        addWing2(16, 1000);
    }

    private void addWing(int num, long price) {
        String key = "wing_" + num;
        Goods goods = GoodsUtils.createGoods(EquipmentType.Wing, key, price);
        wings.add(goods);
        add(goods);
    }

    private void addWing2(int num, long price) {
        String key = "wing2_" + num;
        Goods goods = GoodsUtils.createGoods(EquipmentType.Wing, key, price);
        wings.add(goods);
        add(goods);
    }

    private void initHats() {
        addHat(2, 1800);
        addHat(18, 1000);
        addHat(20, 1000);
        addHat(21, 1800);
        addHat(24, 1800);
        addHat(37, 1000);
        addHat(38, 1000);
        addHat(392, 1800);
        addHat(395, 1800);

        addHat2(12, 1000);
        addHat2(23, 1000);
        addHat2(25, 1000);
        addHat2(42, 1000);
        addHat2(47, 1000);
        addHat2(49, 1000);
    }

    private void addHat(int num, long price) {
        String key = "hat_" + num;
        Goods goods = GoodsUtils.createGoods(EquipmentType.Hat, key, price);
        hats.add(goods);
        add(goods);
    }

    private void addHat2(int num, long price) {
        String key = "hat2_" + num;
        Goods goods = GoodsUtils.createGoods(EquipmentType.Hat, key, price);
        hats.add(goods);
        add(goods);
    }

    private void initEyeWears() {
        addEyeWear(17, 500);
        addEyeWear(20, 500);
        addEyeWear(21, 500);
        addEyeWear(97, 500);
        addEyeWear(282, 500);
    }

    private void addEyeWear(int num, long price) {
        String key = "eye_wear_" + num;
        Goods goods = GoodsUtils.createGoods(EquipmentType.EyeWear, key, price);
        eyeWears.add(goods);
        add(goods);
    }
}
