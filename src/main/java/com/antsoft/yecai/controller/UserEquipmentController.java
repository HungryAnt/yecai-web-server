package com.antsoft.yecai.controller;

import com.antsoft.yecai.model.UserEquipment;
import com.antsoft.yecai.service.UserEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ant on 2016/1/9.
 */
@RestController
@RequestMapping("/v1/userEquipment")
public class UserEquipmentController {
    @Autowired
    private UserEquipmentService userEquipmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserEquipment> getUserEquipments(@RequestParam("userId") String userId) {
        return userEquipmentService.getUserEquipments(userId);
    }
}
