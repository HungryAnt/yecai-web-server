package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.UserVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ant on 2015/9/14.
 */
@RestController
@RequestMapping("/v1/vehicle")
public class VehicleController {
    @Autowired
    private UserVehicleService userVehicleService;

    @RequestMapping(value = "/gift", method = RequestMethod.POST)
    public void gift(@RequestParam(value = "userId") String userId) {
        userVehicleService.gift(userId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestParam(value = "userId") String userId,
                       @RequestParam(value = "num", required = false, defaultValue = "-1") int num) {
        String vehicle;
        if (num == -1) {
            vehicle = userVehicleService.randomVehicle();
        } else {
            vehicle = userVehicleService.toVehicle(num);
        }
        userVehicleService.create(userId, vehicle);
    }

    @RequestMapping(value = "/getVehicles", method = RequestMethod.GET)
    public List<String> getVehicles(@RequestParam(value = "userId") String userId) {
        return userVehicleService.getVehicles(userId);
    }
}
