package com.magus.a4.handler;

import com.magus.a4.pojo.Result;
import com.magus.a4.utils.ResultUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class NoPermissionExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Result handleShiroException(Exception ex) {
        //自定义的返回对象
        return ResultUtil.error("无权限访问");
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(Exception ex) {
        return ResultUtil.error("权限认证失效");
    }

}
