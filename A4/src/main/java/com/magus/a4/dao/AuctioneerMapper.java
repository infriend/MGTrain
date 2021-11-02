package com.magus.a4.dao;

import com.magus.a4.pojo.Auctioneer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctioneerMapper {
    int deleteByPrimaryKey(String username);

    int insert(Auctioneer record);

    int insertSelective(Auctioneer record);

    Auctioneer selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Auctioneer record);

    int updateByPrimaryKey(Auctioneer record);
}