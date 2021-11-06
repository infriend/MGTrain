package com.magus.a4.service;

import com.magus.a4.pojo.Auction;
import com.magus.a4.vo.AuctioneerAuction;

import java.util.List;

public interface AuctioneerService {
    int addAuction(Auction auction);

    int modifyAuction(Auction auction);

    int suspendAuction(String auctionid, String username);

    int restartAuction(String auctionid,String username);

    int finishAuction(String auctionid,String username);

    List<AuctioneerAuction> myAuctions(String username);

}
