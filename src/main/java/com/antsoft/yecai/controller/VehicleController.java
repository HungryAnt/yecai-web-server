package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.UserService;
import com.antsoft.yecai.service.UserVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ant on 2015/9/14.
 */
@RestController
@RequestMapping("/v1/vehicle")
public class VehicleController {
    @Autowired
    private UserVehicleService userVehicleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/gift", method = RequestMethod.POST)
    public void gift(@RequestParam(value = "user_id") String userId) {
        userVehicleService.gift(userId);
    }
}
