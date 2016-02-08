package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.ShitMineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ant on 2016/2/8.
 */
@RestController
@RequestMapping("/v1/shitMine")
public class ShitMineController {
    @Autowired
    private ShitMineService shitMineService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int getUserShitMineCount(@RequestParam("userId") String userId) {
        return shitMineService.getUserShitMineCount(userId);
    }

    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public void exchangeShitMines(@RequestParam("userId") String userId) {
        shitMineService.exchangeShitMines(userId);
    }
}
