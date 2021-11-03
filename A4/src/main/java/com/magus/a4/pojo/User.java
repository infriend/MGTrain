package com.magus.a4.pojo;

import lombok.Data;

@Data
public class User {
    private String username;

    private String password;

    private Short status;

    private String role;
}