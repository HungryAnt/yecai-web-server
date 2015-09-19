package com.antsoft.yecai.controller;

import com.antsoft.yecai.model.User;
import com.antsoft.yecai.service.GameService;
import com.antsoft.yecai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ant on 2015/9/19.
 */
@RestController
@RequestMapping("/v1/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    public void restart() {
        gameService.restart();
    }
}
