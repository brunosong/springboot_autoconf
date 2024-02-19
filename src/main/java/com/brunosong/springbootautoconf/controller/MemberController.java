package com.brunosong.springbootautoconf.controller;

import java.util.UUID;

public class MemberController {

    public String getId(String userName) {
        return userName + " ID is " + UUID.randomUUID();
    }

}
