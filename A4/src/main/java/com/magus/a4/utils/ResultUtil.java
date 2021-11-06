package com.magus.a4.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.magus.a4.pojo.Result;

import java.io.Serializable;

public class ResultUtil implements Serializable {
    public static <T> Result<T> success(){
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("");
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
