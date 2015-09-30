package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.AntiCheatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ant on 2015/9/30.
 */
@RestController
@RequestMapping("/v1/antiCheating")
public class AntiCheatingController {
    @Autowired
    private AntiCheatingService antiCheatingService;

    private boolean enabled = true;

    @RequestMapping(value = "/switchOn", method = RequestMethod.PUT)
    public void switchOn() {
        enabled = true; // 开启反作弊
    }

    @RequestMapping(value = "/switchOff", method = RequestMethod.PUT)
    public void switchOff() {
        enabled = false; // 关闭反作弊
    }

    @RequestMapping(value = "/initClientTimestamp", method = RequestMethod.PUT)
    public void initClientTimestamp(@RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "timestamp") long clientTimestampInS) {
        antiCheatingService.initClientTimestamp(userId, clientTimestampInS);
    }

    @RequestMapping(value = "/checkCheating", method = RequestMethod.PUT)
    public boolean checkIsCheating(@RequestParam(value = "userId") String userId,
                                   @RequestParam(value = "timestamp") long clientTimestampInS) {
        if (!enabled) {
            return false;
        }
        return antiCheatingService.checkCheating(userId, clientTimestampInS);
    }
}