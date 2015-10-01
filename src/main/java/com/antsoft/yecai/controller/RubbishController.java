package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.RubbishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ant on 2015/10/1.
 */
@RestController
@RequestMapping("/v1/rubbish")
public class RubbishController {
    @Autowired
    private RubbishService rubbishService;

    @RequestMapping(value = "/recycle", method = RequestMethod.POST)
    public void recycle() {
        rubbishService.recycle();
    }
}
