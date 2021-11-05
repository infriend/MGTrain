package com.magus.a4.controller;

import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Result;
import com.magus.a4.service.CommonService;
import com.magus.a4.utils.ResultUtil;
import com.magus.a4.vo.Loginvo;
import com.magus.a4.vo.RegisterVo;
import com.magus.a4.vo.Useraccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private CommonService commonService;

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
    public void userRegister(RegisterVo registerVo, HttpServletResponse response) throws IOException {
        int status = commonService.register(registerVo);
        if (status==0){

        } else {
            response.sendRedirect("login");
        }
    }

    @RequestMapping(value = "/")
    public ModelAndView registerPage(ModelAndView mv){
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/auctiondetails")
    public ModelAndView auctiondetails(String auctionid, ModelAndView mv){
        Auction auction = commonService.auctionDetails(auctionid);
        mv.setViewName("auction-details");
        mv.addObject("a", auction);
        return mv;
    }

}
