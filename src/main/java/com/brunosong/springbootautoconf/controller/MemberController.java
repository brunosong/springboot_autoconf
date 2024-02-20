package com.brunosong.springbootautoconf.controller;

import com.brunosong.springbootautoconf.service.MemberService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequestMapping("/member")
public class MemberController implements ApplicationContextAware  {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/getId")
    @ResponseBody
    public String getId(String userName) {
        return userName + " ID is " + UUID.randomUUID();
    }

    @GetMapping("/getUserList")
    @ResponseBody
    public String getUserList() {
        return memberService.getUserNameList().stream().collect(Collectors.joining(","));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* ApplicationContextAware을 구현하게 되면 알아서 주입을 시켜준다. */
        System.out.println(applicationContext);

    }
}
