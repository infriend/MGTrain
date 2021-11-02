package com.magus.a4.dao;

import com.magus.a4.pojo.Auction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionMapper {
    int deleteByPrimaryKey(String auctionid);

    int insert(Auction record);

    int insertSelective(Auction record);

    Auction selectByPrimaryKey(String auctionid);

    int updateByPrimaryKeySelective(Auction record);

    int updateByPrimaryKey(Auction record);
}