package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ant on 2015/9/19.
 */
@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/getAmount", method = RequestMethod.GET)
    public long getAmount(@RequestParam(value = "userId") String userId) {
        return accountService.getAmount(userId);
    }

//    @RequestMapping(value = "/updateAmount", method = RequestMethod.PUT)
//    public void updateAmount(@RequestParam(value = "userId") String userId,
//                             @RequestParam(value = "amount") long amount) {
//        accountService.updateAmount(userId, amount);
//    }

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public void recharge(@RequestParam(value = "userId") String userId,
                         @RequestParam(value = "amount") long amount) {
        accountService.recharge(userId, amount);
    }
}
