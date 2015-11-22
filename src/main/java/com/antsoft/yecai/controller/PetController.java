package com.antsoft.yecai.controller;

import com.antsoft.yecai.model.UserPet;
import com.antsoft.yecai.service.UserPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ant on 2015/11/22.
 */
@RestController
@RequestMapping("/v1/pet")
public class PetController {
    @Autowired
    private UserPetService userPetService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserPet> getByUserId(@RequestParam("userId") String userId) {
        return userPetService.getPetsByUserId(userId);
    }
}
