package com.magus.a4.service;

import com.magus.a4.pojo.Auctionapply;
import com.magus.a4.pojo.Auction;
import com.magus.a4.vo.SimpleAuction;

import java.util.List;

public interface AuctioneerService {
    int addAuction(Auction auction);
    int refuseAuctionApply(String applyid);
    int modifyAuction(Auction auction);
    int suspendAuction(String auctionid, String username);
    int restartAuction(String auctionid,String username);
    int finishAuction(String auctionid,String username);
    List<SimpleAuction> myAuctions(String username);

}
