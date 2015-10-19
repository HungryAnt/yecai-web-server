package com.antsoft.yecai.controller;

import com.antsoft.yecai.model.UserLoginInfo;
import com.antsoft.yecai.model.UserRegisterInfo;
import com.antsoft.yecai.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ant on 2015/10/18.
 */
@RestController
@RequestMapping("/v1/userSecurity")
public class UserSecurityController {
    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody UserRegisterInfo userRegisterInfo) {
        userSecurityService.register(userRegisterInfo);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@Valid @RequestBody UserLoginInfo userLoginInfo) {
        return userSecurityService.login(userLoginInfo);
    }
}
