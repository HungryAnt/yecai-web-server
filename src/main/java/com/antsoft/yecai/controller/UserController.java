package com.antsoft.yecai.controller;

import com.antsoft.yecai.model.User;
import com.antsoft.yecai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ant on 2015/9/18.
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findByUserName", method = RequestMethod.GET)
    public List<User> findByUserName(@RequestParam(value = "userName") String userName) {
        return userService.findByUserName(userName);
    }

    @RequestMapping(value = "/getByLoginName", method = RequestMethod.GET)
    public User getByLoginName(@RequestParam(value = "loginName") String loginName) {
        return userService.getByLoginName(loginName);
    }
}
