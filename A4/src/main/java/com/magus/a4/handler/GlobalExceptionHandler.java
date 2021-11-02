package com.magus.a4.handler;

import com.magus.a4.pojo.Result;
import com.magus.a4.utils.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public Result globalExceptionHandler(HttpServletRequest request, GlobalException e){
        return ResultUtil.error(e.getErrMsg());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public Result handleConstraintViolationException(ConstraintViolationException e){
        StringBuilder msg = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            msg.append("[").append(message).append("]");
        }

        return ResultUtil.error(msg.toString());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        if(bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            for(ObjectError error : bindingResult.getAllErrors()){
                String message = error.getDefaultMessage();
                msg.append(message).append(",");
            }
            msg.deleteCharAt(msg.length()-1);
            return ResultUtil.error(msg.toString());
        }
        else{
            return ResultUtil.error("我猜执行不到这一步");
        }

    }
}
