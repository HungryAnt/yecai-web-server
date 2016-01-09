package com.antsoft.yecai.service;

import com.antsoft.framework.model.PageResult;
import com.antsoft.framework.utils.PageUtility;
import com.antsoft.yecai.mapper.Goods;
import com.antsoft.yecai.service.goods.*;
import com.antsoft.yecai.type.EquipmentType;
import com.antsoft.yecai.type.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // goods services
    @Autowired
    private NewVehicleGoodsService newVehicleGoodsService;

    @Autowired
    private VehicleGoodsService vehicleGoodsService;

    @Autowired
    private NostalgicVehicleGoodsService nostalgicVehicleGoodsService;

    @Autowired
    private PetGoodsService petGoodsService;

    @Autowired
    private EquipmentGoodsService equipmentGoodsService;

    private List<List<Goods>> goodsListCollection;

    public ShoppingService() {

    }

    @PostConstruct
    private void initGoods() {
        List<GoodsServiceBase> goodsServices = Arrays.asList(
                newVehicleGoodsService,
                vehicleGoodsService,
                nostalgicVehicleGoodsService,
                petGoodsService,
                equipmentGoodsService
        );
        goodsListCollection = new ArrayList<>();
        for (GoodsServiceBase goodsService : goodsServices) {
            goodsListCollection.add(goodsService.getGoods());
        }
    }

    public PageResult<Goods> getNewVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(newVehicleGoodsService.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(vehicleGoodsService.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getNostalgicVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(nostalgicVehicleGoodsService.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getPets(int pageNo, int pageSize) {
        return getGoodsPageResult(petGoodsService.getGoods(), pageNo, pageSize);
    }

    public PageResult<Goods> getWings(int pageNo, int pageSize) {
        return getGoodsPageResult(equipmentGoodsService.getWings(), pageNo, pageSize);
    }

    public PageResult<Goods> getEyeWears(int pageNo, int pageSize) {
        return getGoodsPageResult(equipmentGoodsService.getEyeWears(), pageNo, pageSize);
    }

    public PageResult<Goods> getHats(int pageNo, int pageSize) {
        return getGoodsPageResult(equipmentGoodsService.getHats(), pageNo, pageSize);
    }

    private PageResult<Goods> getGoodsPageResult(List<Goods> goods, int pageNo, int pageSize) {
        int offset = PageUtility.getOffset(pageNo, pageSize);
        int toIndex = Math.min(offset + pageSize, goods.size());
        List<Goods> goodsList = goods.subList(offset, toIndex);
        return PageResult.createResponse(goodsList, goods.size(), pageNo, pageSize);
    }

    @Transactional
    public void buy(String userId, String key) {
        Goods goods = findGoods(key);
        long price = goods.getPrice();
        long amount = accountService.getAmountForUpdate(userId);
        if (amount >= price) {
            accountService.decreaseAmount(userId, price);
            if (goods.getGoodsType() == GoodsType.Pet) {
                userPetService.create(userId, key);
            } else if (goods.getGoodsType() == GoodsType.Vehicle) {
                userVehicleService.create(userId, key);
            } else if (goods.getGoodsType() == GoodsType.Wing) {
                userEquipmentService.create(userId, EquipmentType.Wing, key);
            } else if (goods.getGoodsType() == GoodsType.Hat) {
                userEquipmentService.create(userId, EquipmentType.Hat, key);
            } else if (goods.getGoodsType() == GoodsType.EyeWear) {
                userEquipmentService.create(userId, EquipmentType.EyeWear, key);
            } else {
                throw new IllegalArgumentException("key:" + key);
            }
            recordService.createGoodsPaymentRecord(userId, key, price);
        }
    }

    private Goods findGoods(String key) {
        for (List<Goods> goodsList : goodsListCollection) {
            for (Goods goods : goodsList) {
                if (goods.getKey().equals(key)) {
                    return goods;
                }
            }
        }
        throw new IllegalArgumentException("wrong key:" + key);
    }
}
