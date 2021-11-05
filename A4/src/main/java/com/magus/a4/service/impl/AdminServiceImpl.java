package com.magus.a4.service.impl;

import com.magus.a4.dao.UserMapper;
import com.magus.a4.pojo.User;
import com.magus.a4.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteAccount(String username) {
        return userMapper.updateStatusByUsername((short) 0, username);
    }

    @Override
    public int resetPasswd(String username, String passwd) {
        return userMapper.resetPasswdByUsername(username, passwd);
    }

    @Override
    public int checkAuctioneer(String username, short decision) {
        return userMapper.updateStatusByUsername(decision, username);
    }

    @Override
    public List<User> getActiveUserList() {
        return userMapper.getActiveUserList();
    }

}
