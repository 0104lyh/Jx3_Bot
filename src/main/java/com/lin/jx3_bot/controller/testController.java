package com.lin.jx3_bot.controller;

import com.alibaba.fastjson.JSON;
import com.lin.jx3_bot.service.QueryDaily;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @RequestMapping(value = "daily")
    public JSON daily(){
        return QueryDaily.getDailyJson();
    }
}
