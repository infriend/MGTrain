package com.magus.a4.dao;

import com.magus.a4.pojo.Auction;
import com.magus.a4.vo.SimpleAuction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    int deleteByPrimaryKey(String auctionid);

    int insert(Auction record);

    int insertSelective(Auction record);

    Auction selectByPrimaryKey(String auctionid);

    int updateByPrimaryKeySelective(Auction record);

    int updateByPrimaryKey(Auction record);

    List<SimpleAuction> getAuctionsByAuctioneer(String auctioneer);
}