package com.magus.a4.service.impl;

import com.magus.a4.dao.BidingpriceMapper;
import com.magus.a4.dao.UserMapper;
import com.magus.a4.handler.GlobalException;
import com.magus.a4.pojo.User;
import com.magus.a4.service.CommonService;
import com.magus.a4.utils.JwtUtil;
import com.magus.a4.vo.Loginvo;
import com.magus.a4.vo.SimpleBidingPrice;
import com.magus.a4.vo.Useraccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BidingpriceMapper bidingpriceMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Useraccount login(Loginvo loginvo) {
        User user = userMapper.selectByPrimaryKey(loginvo.getUsername());
        if (user == null){
            throw new GlobalException("用户名不存在");
        } else if (!user.getPassword().equals(loginvo.getPassword())){
            throw new GlobalException("密码错误");
        }

        String token = jwtUtil.createTokenByWxAccount(user);
        Useraccount useraccount = new Useraccount();
        useraccount.setUsername(user.getUsername());
        useraccount.setRole(user.getRole());
        useraccount.setToken(token);

        return useraccount;
    }

    @Override
    public List<SimpleBidingPrice> getBidingList(String auctionid) {
        return bidingpriceMapper.getBidingList(auctionid);
    }
}
