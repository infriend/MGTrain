package com.magus.a4.service;

import com.magus.a4.vo.Loginvo;
import com.magus.a4.vo.SimpleBidingPrice;
import com.magus.a4.vo.Useraccount;

import java.util.List;

public interface CommonService {
    Useraccount login(Loginvo loginvo);

    List<SimpleBidingPrice> getBidingList(String auctionid);
}
