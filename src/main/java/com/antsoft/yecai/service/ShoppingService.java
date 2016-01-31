package com.antsoft.yecai.service;

import com.antsoft.framework.model.PageResult;
import com.antsoft.yecai.mapper.GoodsMapper;
import com.antsoft.yecai.model.Goods;
import com.antsoft.yecai.type.EquipmentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ant on 2015/9/22.
 */
@Service
public class ShoppingService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserVehicleService userVehicleService;

    @Autowired
    private UserPetService userPetService;

    @Autowired
    private UserEquipmentService userEquipmentService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Transactional
    public void buy(String userId, String key) {
        logger.info("start ShoppingService.buy userId:{} key:{}", userId, key);
        Goods goods = goodsMapper.getByKey(key);
        if (goods == null) {
            throw new RuntimeException("goods not found, key:" + key);
        }
        long price = goods.getPrice();
        long amount = accountService.getAmountForUpdate(userId);
        if (amount >= price) {
            accountService.decreaseAmount(userId, price);
            if (goods.getEquipmentType() == EquipmentType.Pet) {
                userPetService.create(userId, key);
            } else if (goods.getEquipmentType() == EquipmentType.Vehicle) {
                userVehicleService.create(userId, key);
            } else if (goods.getEquipmentType() == EquipmentType.Underpan
                    || goods.getEquipmentType() == EquipmentType.Wing
                    || goods.getEquipmentType() == EquipmentType.EyeWear
                    || goods.getEquipmentType() == EquipmentType.Hat
                    || goods.getEquipmentType() == EquipmentType.EarWear
                    || goods.getEquipmentType() == EquipmentType.Handheld
                    || goods.getEquipmentType() == EquipmentType.Background
                    || goods.getEquipmentType() == EquipmentType.Foreground
                    ) {
                userEquipmentService.create(userId, goods.getEquipmentType(), key);
            } else {
                throw new IllegalArgumentException("can not create Equipment, key:" + key);
            }
            recordService.createGoodsPaymentRecord(userId, key, price);
        }
    }

    public PageResult<Goods> getGoods(EquipmentType equipmentType, int pageNo, int pageSize) {
        logger.info("start ShoppingService.getGoods equipmentType:{} pageNo:{} pageSize:{}",
                equipmentType, pageNo, pageSize);
        List<Goods> goodsList = goodsMapper.getByEquipmentType(equipmentType, pageNo, pageSize);
        int count = goodsMapper.countByEquipmentType(equipmentType);
        return PageResult.createResponse(goodsList, count, pageNo, pageSize);
    }
}
