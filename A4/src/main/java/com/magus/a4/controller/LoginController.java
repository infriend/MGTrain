package com.magus.a4.controller;

import com.magus.a4.pojo.Result;
import com.magus.a4.service.CommonService;
import com.magus.a4.utils.ResultUtil;
import com.magus.a4.vo.Loginvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class LoginController {
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "common/login", method = RequestMethod.POST)
    public Result login(Loginvo loginvo){
        return ResultUtil.success(commonService.login(loginvo));
    }





}
