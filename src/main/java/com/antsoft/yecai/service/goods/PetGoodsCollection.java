package com.antsoft.yecai.service.goods;

import com.antsoft.yecai.type.EquipmentType;

/**
 * Created by ant on 2016/1/9.
 */
public class PetGoodsCollection extends GoodsBaseCollection {
    public PetGoodsCollection() {
        initPets();
    }

    private void initPets() {
        long price = 1200;
//        addPet("c1", price);
//        addPet("c2", price);
//        addPet("c3", price);
        addPet("f1", price);
        addPet("f2", price);
        addPet("f3", price);

        for (int i = 1; i <= 23; i++) {
            addPet("g" + i, price);
        }
    }

    private void addPet(String id, long price) {
        String key = "pet_" + id;
        add(GoodsUtils.createGoods(EquipmentType.Pet, key, price));
    }
}
