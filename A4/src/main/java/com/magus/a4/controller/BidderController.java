package com.magus.a4.controller;

import com.magus.a4.pojo.Result;
import com.magus.a4.service.BidderService;
import com.magus.a4.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bidder")
public class BidderController {
    @Autowired
    private BidderService bidderService;

    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public Result enrollAuction(String auctionid, HttpServletRequest request){
        //忽略支付API的调用和确认，点击后就算已经确认过支付完成了
        String username = (String) request.getSession().getAttribute("username");
        int s = bidderService.enrollAuction(auctionid, username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("报名失败");
        }
    }

    @RequestMapping(value = "/bidding", method = RequestMethod.POST)
    public Result bidding(String auctionid){

        return null;
    }

    @RequestMapping(value = "/confirmhammer", method = RequestMethod.POST)
    public Result confirmHammer(String auctionid){
        return null;
    }

    @RequestMapping(value = "/myauctions", method = RequestMethod.POST)
    public Result getMyAuctions(String auctionid){
        return null;
    }


}
