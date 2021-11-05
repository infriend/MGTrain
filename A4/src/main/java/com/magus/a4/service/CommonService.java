package com.magus.a4.service;

import com.magus.a4.pojo.Auction;
import com.magus.a4.vo.Loginvo;
import com.magus.a4.vo.RegisterVo;
import com.magus.a4.vo.SimpleBidingPrice;
import com.magus.a4.vo.Useraccount;

import java.util.List;

public interface CommonService {
    int login(Loginvo loginvo);

    List<SimpleBidingPrice> getBidingList(String auctionid);

    int register(RegisterVo registerVo);

    Auction auctionDetails(String auctionid);
}
