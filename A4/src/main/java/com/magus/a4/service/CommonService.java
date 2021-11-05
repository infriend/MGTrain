package com.magus.a4.service;

import com.magus.a4.pojo.Auction;
import com.magus.a4.vo.*;

import java.util.List;

public interface CommonService {
    int login(Loginvo loginvo);

    List<SimpleBidingPrice> getBidingList(String auctionid);

    int register(RegisterVo registerVo) throws Exception;

    Auction auctionDetails(String auctionid);

    List<SimpleAuction> getActiveAuctions();
}
