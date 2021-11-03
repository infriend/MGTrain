package com.magus.a4.controller;

import com.magus.a4.pojo.Auctionapply;
import com.magus.a4.pojo.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/seller")
public class SellerController {

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @RequiresRoles("user")
    public Result applyAuction(Auctionapply auctionapply){
        return null;
    }

    @RequestMapping(value = "/giveup", method = RequestMethod.POST)
    @RequiresRoles("user")
    public Result giveUpAuction(String applyid){
        return null;
    }

    @RequestMapping(value = "/mylist", method = RequestMethod.POST)
    @RequiresRoles("user")
    public Result getMyAuctions(){
        return null;
    }
}
