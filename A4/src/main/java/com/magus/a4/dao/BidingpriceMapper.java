package com.magus.a4.dao;

import com.magus.a4.pojo.Bidingprice;
import com.magus.a4.vo.SimpleBidingPrice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidingpriceMapper {
    int deleteByPrimaryKey(String bidinguid);

    int insert(Bidingprice record);

    int insertSelective(Bidingprice record);

    Bidingprice selectByPrimaryKey(String bidinguid);

    int updateByPrimaryKeySelective(Bidingprice record);

    int updateByPrimaryKey(Bidingprice record);

    List<SimpleBidingPrice> getBidingList(String auctionid);

    Bidingprice getMaxBidingPrice(String auctionid);
}