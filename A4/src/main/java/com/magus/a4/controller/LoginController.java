package com.magus.a4.controller;

import com.magus.a4.dao.EnrollmentMapper;
import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Enrollment;
import com.magus.a4.pojo.Result;
import com.magus.a4.service.CommonService;
import com.magus.a4.utils.ResultUtil;
import com.magus.a4.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @RequestMapping(value = "/clogin", method = RequestMethod.POST)
    public Map<String,Object> login(@ModelAttribute Loginvo loginvo, HttpServletRequest request){
        int status = commonService.login(loginvo);
        if (status == 0) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg", "用户名错误");
            return map;
        } else if (status == 1) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg", "密码错误");
            return map;
        } else {
            request.getSession().setAttribute("username", loginvo.getUsername());
            request.getSession().setAttribute("role", loginvo.getRole());
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg", "yes");
            return map;
        }
    }

    @RequestMapping(value = "/userregister", method = RequestMethod.POST)
    public void userRegister(RegisterVo registerVo, HttpServletResponse response) throws Exception {
        int status = 0;
        try{
            status = commonService.register(registerVo);
        } catch (Exception e) {
            status = 0;
        }
        if (status==0){
            response.sendRedirect("/registerpage");
        } else {
            response.sendRedirect("/");
        }
    }

    @RequestMapping(value = "/")
    public ModelAndView loginPage(ModelAndView mv){
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/auctiondetails")
    public ModelAndView auctionDetails(String auctionid, ModelAndView mv, HttpServletRequest request){
        Auction auction = commonService.auctionDetails(auctionid);
        String role = (String) request.getSession().getAttribute("role");
        String username = (String) request.getSession().getAttribute("username");
        if (role.equals("auctioneer")){
            mv.setViewName("auction-auctioneer");
        } else if (role.equals("user")){
            Enrollment e = enrollmentMapper.getByAuctionidAndUsername(auctionid, username);
            if (e != null){
                if (auction.getStatus()==1){
                    mv.setViewName("auction-details");
                } else if (auction.getStatus()==2){
                    if (username.equals(auction.getWinner())){
                        mv.setViewName("auction-confirm");
                    } else {
                        mv.setViewName("auction-over");
                    }
                }
            } else {
                mv.setViewName("auction-unenrolled");
            }
        }
        mv.addObject("a", auction);
        return mv;
    }

    @RequestMapping(value = "/registerpage")
    public ModelAndView registerPage(ModelAndView mv){
        mv.setViewName("register");
        return mv;
    }

    @RequestMapping(value = "/auctionlist")
    public ModelAndView auctionListPage(ModelAndView mv){
        List<SimpleAuction> auctionList = commonService.getActiveAuctions();
        mv.addObject("ats", auctionList);
        mv.setViewName("AuctionList");
        return mv;
    }

    @RequestMapping(value = "/bidinglist")
    @ResponseBody
    public Result bidingList(String auctionid){
        List<SimpleBidingPrice> bidingPrices = commonService.getBidingList(auctionid);
        return ResultUtil.success(bidingPrices);
    }
}
