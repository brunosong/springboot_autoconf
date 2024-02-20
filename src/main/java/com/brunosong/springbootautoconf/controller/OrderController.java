package com.brunosong.springbootautoconf.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/order")
public class OrderController {

    private final ApplicationContext applicationContext;

    public OrderController(ApplicationContext applicationContext) {
        System.out.println("ApplicationContext는 이렇게 해도 스프링컨테이너가 알아서 주입을 시켜준다.");
        this.applicationContext = applicationContext;
    }

    @PostMapping
    @ResponseBody
    public String order(String userName) {
        return userName + " is Ordered";
    }

}
