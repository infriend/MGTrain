package com.magus.a4.dao;

import com.magus.a4.pojo.Auction;
import com.magus.a4.vo.SimpleAuction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionMapper {
    int deleteByPrimaryKey(String auctionid);

    int insert(Auction record);

    int insertSelective(Auction record);

    Auction selectByPrimaryKey(String auctionid);

    int updateByPrimaryKeySelective(Auction record);

    int updateByPrimaryKey(Auction record);

    List<SimpleAuction> getAuctionsByAuctioneer(String auctioneer);

    List<Auction> getAuctionsGoingOn();

    List<SimpleAuction> getAuctionsByUser(String username);

    List<SimpleAuction> getAuctionsByStatus(short status);
}