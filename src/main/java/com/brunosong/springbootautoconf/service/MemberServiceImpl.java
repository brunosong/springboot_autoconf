package com.brunosong.springbootautoconf.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MemberServiceImpl implements MemberService {

    @Override
    public List<String> getUserNameList() {
        return Arrays.asList("Bruno","Kim","Wan");
    }

}
