package com.antsoft.yecai.controller;

import com.antsoft.yecai.service.PromotionLinkStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ant on 2015/11/7.
 */
@Controller
@RequestMapping("/link")
public class FriendlyLinkController {
    @Autowired
    private PromotionLinkStatService promotionLinkStatService;

    @RequestMapping("/shop")
    public String shop() {
        promotionLinkStatService.increaseCount("yecai-shop");
        return "redirect:/yecai/underconstruction.html";
    }

    @RequestMapping("/tetris")
    public String tetrisGame() {
        promotionLinkStatService.increaseCount("tetris");
        return "redirect:http://180.76.152.79/";
    }
}
