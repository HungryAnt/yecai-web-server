package com.antsoft.yecai.service;

import com.antsoft.framework.model.PageResult;
import com.antsoft.framework.utils.PageUtility;
import com.antsoft.yecai.mapper.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private RecordService recordService;

    private List<Goods> vehicles;
    private List<Goods> nostalgicVehicles;

    public ShoppingService() {
        vehicles = new ArrayList<>();
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

        nostalgicVehicles = new ArrayList<>();
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

    private void addVehicle(int num, long price) {
        String key = String.format("vehicle_%s", num);
        Goods goods = new Goods();
        goods.setKey(key);
        goods.setPrice(price);
        vehicles.add(goods);
    }

    private void addNostalgicVehicle(int num, long price) {
        String key = String.format("vehicle2_%s", num);
        Goods goods = new Goods();
        goods.setKey(key);
        goods.setPrice(price);
        nostalgicVehicles.add(goods);
    }

    private void addDragon(String name, long price) {
        String key = String.format("dragon_%s", name);
        Goods goods = new Goods();
        goods.setKey(key);
        goods.setPrice(price);
        vehicles.add(goods);
    }

    public PageResult<Goods> getVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(vehicles, pageNo, pageSize);
    }

    public PageResult<Goods> getNostalgicVehicles(int pageNo, int pageSize) {
        return getGoodsPageResult(nostalgicVehicles, pageNo, pageSize);
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
            userVehicleService.create(userId, key);
            recordService.createGoodsPaymentRecord(userId, key, price);
        }
    }

    private Goods findGoods(String key) {
        for (List<Goods> goodsList : Arrays.asList(vehicles, nostalgicVehicles)) {
            for (Goods goods : goodsList) {
                if (goods.getKey().equals(key)) {
                    return goods;
                }
            }
        }
        throw new IllegalArgumentException("wrong key:" + key);
    }
}
