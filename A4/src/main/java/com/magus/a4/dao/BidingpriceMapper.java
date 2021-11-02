package com.magus.a4.dao;

import com.magus.a4.pojo.Bidingprice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BidingpriceMapper {
    int deleteByPrimaryKey(String bidinguid);

    int insert(Bidingprice record);

    int insertSelective(Bidingprice record);

    Bidingprice selectByPrimaryKey(String bidinguid);

    int updateByPrimaryKeySelective(Bidingprice record);

    int updateByPrimaryKey(Bidingprice record);
}