package com.magus.a4.controller;

import com.magus.a4.pojo.Result;
import com.magus.a4.service.BidderService;
import com.magus.a4.utils.ResultUtil;
import com.magus.a4.vo.SimpleAuction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

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
    public Result bidding(String auctionid, BigDecimal price, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int s = bidderService.bidding(auctionid, username, price);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("报价失败");
        }
    }

    @RequestMapping(value = "/confirmhammer", method = RequestMethod.POST)
    public Result confirmHammer(String auctionid, short status, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int s = 0;
        if (status == 3 || status == 4) {
            s = bidderService.confirmHammer(auctionid, username, status);
        }
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("确认失败");
        }
    }

    @RequestMapping(value = "/myauctions")
    public ModelAndView getMyAuctions(HttpServletRequest request, ModelAndView mv){
        String username = (String) request.getSession().getAttribute("username");
        List<SimpleAuction> auctionList = bidderService.getMyAuctions(username);
        mv.addObject("ats", auctionList);
        mv.setViewName("UserAuctionList");
        return mv;
    }

    @RequestMapping(value = "/home")
    public ModelAndView homePage(ModelAndView mv){
        mv.setViewName("user_index");
        return mv;
    }

    @RequestMapping(value = "/auctionlist")
    public String auctionList(){
        return "redirect:/auctionlist";
    }

    @RequestMapping(value = "/bidinglist")
    public String bidingList(){
        return "redirect:/bidinglist";
    }


}
