package com.magus.a4.service;

import com.magus.a4.vo.SimpleAuction;

import java.math.BigDecimal;
import java.util.List;

public interface BidderService {
    int enrollAuction(String auctionid, String username);

    int bidding(String auctionid, String username, BigDecimal price);

    int confirmHammer(String auctionid, String username, int status);

    List<SimpleAuction> getMyAuctions(String username);

}
