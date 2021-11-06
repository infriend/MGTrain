package com.magus.a4.controller;

import com.magus.a4.pojo.Result;
import com.magus.a4.pojo.User;
import com.magus.a4.service.AdminService;
import com.magus.a4.utils.ResultUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteAccount(String username){
        int s = adminService.deleteAccount(username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("删除失败");
        }
    }

    @RequestMapping(value = "/resetpw", method = RequestMethod.POST)
    public String resetPasswd(String username){
        String passwd = RandomStringUtils.randomAlphanumeric(6);
        adminService.resetPasswd(username, passwd);
        return passwd;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public Result check(String username, short decision){
        int s = adminService.checkAuctioneer(username, decision);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("审查失败");
        }
    }

    @RequestMapping("/home")
    public ModelAndView homePage(ModelAndView mv, HttpServletRequest request){
        mv.setViewName("admin_index");
        return mv;
    }

    @RequestMapping("/accountlist")
    public ModelAndView accountList(ModelAndView mv, HttpServletRequest request){
        mv.setViewName("account-list");
        List<User> userList = adminService.getActiveUserList();
        mv.addObject("accounts", userList);
        return mv;
    }

    @RequestMapping("/checklist")
    public ModelAndView checkList(ModelAndView mv){
        mv.setViewName("CheckList");
        List<User> userList = adminService.getUncheckedList();
        mv.addObject("accounts", userList);
        return mv;
    }
}
