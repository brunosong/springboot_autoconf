package com.brunosong.springbootautoconf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/order")
public class OrderController {

    @PostMapping
    @ResponseBody
    public String order(String userName) {
        return userName + " is Ordered";
    }

}
