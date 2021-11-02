package com.magus.a4.utils;

import com.magus.a4.pojo.Result;

public class ResultUtil {
    public static <T> Result<T> success(){
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String message){
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
