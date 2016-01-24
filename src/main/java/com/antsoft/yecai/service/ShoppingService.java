package com.antsoft.yecai.service;

import com.antsoft.framework.model.PageResult;
import com.antsoft.framework.utils.PageUtility;
import com.antsoft.yecai.mapper.GoodsMapper;
import com.antsoft.yecai.model.Goods;
import com.antsoft.yecai.service.goods.*;
import com.antsoft.yecai.type.EquipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ant on 2015/9/22.
 */
@Service
public class ShoppingService {
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

    private NewVehicleGoodsCollection newVehicleGoodsCollection = new NewVehicleGoodsCollection();
    private VehicleGoodsCollection vehicleGoodsCollection = new VehicleGoodsCollection();
    private NostalgicVehicleGoodsCollection nostalgicVehicleGoodsCollection = new NostalgicVehicleGoodsCollection();
    private PetGoodsCollection petGoodsCollection = new PetGoodsCollection();
    private EquipmentGoodsCollection equipmentGoodsCollection = new EquipmentGoodsCollection();

    private Map<String, Goods> goodsMap = new HashMap<>();

    @PostConstruct
    private void initGoods() {
        List<GoodsBaseCollection> goodsCollections = Arrays.asList(
                newVehicleGoodsCollection,
                vehicleGoodsCollection,
                nostalgicVehicleGoodsCollection,
                petGoodsCollection,
                equipmentGoodsCollection
        );
        for (GoodsBaseCollection collection : goodsCollections) {
            for (Goods goods : collection.getGoods()) {
                goodsMap.put(goods.getKey(), goods);
            }
        }
    }

    @Transactional
    public void buy(String userId, String key) {
        Goods goods = findGoods(key);
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
                    || goods.getEquipmentType() == EquipmentType.Handheld) {
                userEquipmentService.create(userId, goods.getEquipmentType(), key);
            } else {
                throw new IllegalArgumentException("key:" + key);
            }
            recordService.createGoodsPaymentRecord(userId, key, price);
        }
    }

    private Goods findGoods(String key) {
        if (goodsMap.containsKey(key)) {
            return goodsMap.get(key);
        }
        throw new IllegalArgumentException("wrong key:" + key);
    }

    private PageResult<Goods> getGoodsPageResult(List<Goods> goods, int pageNo, int pageSize) {
        int offset = PageUtility.getOffset(pageNo, pageSize);
        int toIndex = Math.min(offset + pageSize, goods.size());
        List<Goods> goodsList = goods.subList(offset, toIndex);
        return PageResult.createResponse(goodsList, goods.size(), pageNo, pageSize);
    }

    public PageResult<Goods> getNewVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(newVehicleGoodsCollection.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(vehicleGoodsCollection.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getNostalgicVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(nostalgicVehicleGoodsCollection.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getPets(int pageNo, int pageSize) {
        return getGoodsPageResult(petGoodsCollection.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getWings(int pageNo, int pageSize) {
        return getGoodsPageResult(equipmentGoodsCollection.getWings(), pageNo, pageSize);
    }

    public PageResult<Goods> getEyeWears(int pageNo, int pageSize) {
        return getGoodsPageResult(equipmentGoodsCollection.getEyeWears(), pageNo, pageSize);
    }

    public PageResult<Goods> getHats(int pageNo, int pageSize) {
        return getGoodsPageResult(equipmentGoodsCollection.getHats(), pageNo, pageSize);
    }

    public PageResult<Goods> getGoods(EquipmentType equipmentType, int pageNo, int pageSize) {
        List<Goods> goodsList = goodsMapper.getByEquipmentType(equipmentType, pageNo, pageSize);
        int count = goodsMapper.countByEquipmentType(equipmentType);
        return PageResult.createResponse(goodsList, count, pageNo, pageSize);
    }
}
