package com.magus.a4.service.impl;

import com.magus.a4.dao.AuctionMapper;
import com.magus.a4.dao.BidingpriceMapper;
import com.magus.a4.dao.UserMapper;
import com.magus.a4.dao.UserinfoMapper;
import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.User;
import com.magus.a4.pojo.Userinfo;
import com.magus.a4.service.CommonService;
import com.magus.a4.vo.Loginvo;
import com.magus.a4.vo.RegisterVo;
import com.magus.a4.vo.SimpleAuction;
import com.magus.a4.vo.SimpleBidingPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private BidingpriceMapper bidingpriceMapper;

    @Autowired
    private AuctionMapper auctionMapper;

    @Override
    public int login(Loginvo loginvo) {
        User user = userMapper.selectByPrimaryKey(loginvo.getUsername());
        if (user == null){
            return 0;
        } else if (!user.getPassword().equals(loginvo.getPassword())){
            return 1;
        }
        if (user.getStatus()!=2){
            return 0;
        }
        return 2;
    }

    @Override
    public List<SimpleBidingPrice> getBidingList(@RequestParam("auctionid") String auctionid) {
        return bidingpriceMapper.getBidingList(auctionid);
    }

    @Override
    public int register(RegisterVo registerVo) throws Exception {
        User user = new User();
        user.setUsername(registerVo.getUsername());
        user.setPassword(registerVo.getPassword());
        user.setRole(registerVo.getRole());
        if (user.getRole().equals("auctioneer")) {
            user.setStatus((short) 1);
        } else user.setStatus((short) 2);

        Userinfo userinfo = new Userinfo();
        userinfo.setUsername(user.getUsername());
        userinfo.setSex(registerVo.getSex());
        userinfo.setPhone(registerVo.getPhone());

        int s = userMapper.insert(user);
        if (s==0){
            return s;
        } else {
            s &= userinfoMapper.insert(userinfo);
        }
        return s;
    }

    @Override
    public Auction auctionDetails(String auctionid) {
        return auctionMapper.selectByPrimaryKey(auctionid);
    }

    @Override
    public List<SimpleAuction> getActiveAuctions() {
        return auctionMapper.getAuctionsByStatus((short) 1);
    }
}
