package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ant on 2016/2/9.
 */
@RestController
@RequestMapping("/v1/redPacket")
public class RedPacketController {
    @Autowired
    private RedPacketService redPacketService;

    @RequestMapping(value = "/obtainDailyRedPacket", method = RequestMethod.POST)
    public void obtainDailyRedPacket(@RequestParam("userId") String userId) {
        redPacketService.obtainDailyRedPacket(userId);
    }
}
