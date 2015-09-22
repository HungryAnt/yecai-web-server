package com.antsoft.yecai.service;

import com.antsoft.framework.model.PageResult;
import com.antsoft.framework.utils.PageUtility;
import com.antsoft.yecai.mapper.Goods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ant on 2015/9/22.
 */
@Service
public class ShoppingService {
    private List<Goods> allVehicles;

    public ShoppingService() {
        allVehicles = new ArrayList<>();
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
        addVehicle(604, 9900);
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
    }

    private void addVehicle(int num, long price) {
        String key = String.format("vehicle_%s", num);
        Goods goods = new Goods();
        goods.setKey(key);
        goods.setPrice(price);
        allVehicles.add(goods);
    }

    public PageResult<Goods> getVehicles(int pageNo, int pageSize) {
        int offset = PageUtility.getOffset(pageNo, pageSize);
        List<Goods> vehicles = allVehicles.subList(offset, offset + pageSize);
        return PageResult.createResponse(vehicles, allVehicles.size(), pageNo, pageSize);
    }
}
