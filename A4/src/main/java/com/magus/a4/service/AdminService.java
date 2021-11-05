package com.magus.a4.service;

import com.magus.a4.pojo.User;

import java.util.List;

public interface AdminService {
    int deleteAccount(String username);

    int resetPasswd(String username, String passwd);

    int checkAuctioneer(String username, short decision);

    List<User> getActiveUserList();
}
