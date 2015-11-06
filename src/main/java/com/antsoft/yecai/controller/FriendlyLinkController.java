package com.antsoft.yecai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ant on 2015/11/7.
 */
@Controller
@RequestMapping("/link")
public class FriendlyLinkController {
    @RequestMapping("/shop")
    public String shop() {
        // todo
        return "ok";
    }

    @RequestMapping("/tetris")
    public String tetrisGame() {
        return "redirect:http://180.76.152.79/";
    }
}
