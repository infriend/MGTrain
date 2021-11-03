package com.magus.a4.controller;

import com.magus.a4.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAccount(String username){
        return null;
    }

    @RequestMapping(value = "resetpw", method = RequestMethod.POST)
    public Result resetPasswd(String username){
        return null;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Result check(String username){
        return null;
    }
}
