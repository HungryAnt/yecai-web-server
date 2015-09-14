package com.antsoft.yecai.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by ant on 2015/9/14.
 */
@RestController
@RequestMapping("/v1/award")
public class AwardController {
    @RequestMapping(value = "/generate_car", method = RequestMethod.POST)
    public void generateCar(@RequestParam(value = "user_id") String userId) {

    }
}
