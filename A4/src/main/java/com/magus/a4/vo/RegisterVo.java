package com.magus.a4.vo;

import lombok.Data;

@Data
public class RegisterVo {
    private String username;
    private String password;
    private String role;
    private short sex;
    private String phone;
}
