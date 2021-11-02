package com.magus.a4.utils;

import java.util.Date;
import java.util.UUID;

/***
 * 这是一个用于生成UUID的工具类
 * 去除‘-’生成32位字符组成的UUID
 */

public class UUIDGeneratorUtil {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
