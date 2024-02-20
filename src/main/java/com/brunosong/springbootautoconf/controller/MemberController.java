package com.brunosong.springbootautoconf.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Component
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/getId")
    @ResponseBody
    public String getId(String userName) {
        return userName + " ID is " + UUID.randomUUID();
    }

}
