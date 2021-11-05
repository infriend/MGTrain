package com.magus.a4.controller;

import com.magus.a4.pojo.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAccount(String username){
        return null;
    }

    @RequestMapping(value = "resetpw", method = RequestMethod.POST)
    public Result resetPasswd(String username){
        String passwd = RandomStringUtils.randomAlphanumeric(6);
        return null;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Result check(String username, short decision){
        return null;
    }
}
