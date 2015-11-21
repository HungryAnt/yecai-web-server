package com.antsoft.yecai.controller;

import com.antsoft.framework.model.PageResult;
import com.antsoft.yecai.mapper.Goods;
import com.antsoft.yecai.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ant on 2015/9/22.
 */
@RestController
@RequestMapping("/v1/shopping")
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public PageResult<Goods> getVehicles(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
        return shoppingService.getVehicles(pageNo, pageSize);
    }

    @RequestMapping(value = "/nostalgicVehicles", method = RequestMethod.GET)
    public PageResult<Goods> getNostalgicVehicless(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
        return shoppingService.getNostalgicVehicles(pageNo, pageSize);
    }

    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public PageResult<Goods> getPets(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
        return shoppingService.getPets(pageNo, pageSize);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(@RequestParam("userId") String userId,
                    @RequestParam("key") String key) {
        shoppingService.buy(userId, key);
    }
}
