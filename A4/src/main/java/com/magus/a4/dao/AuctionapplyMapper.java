package com.magus.a4.dao;

import com.magus.a4.pojo.Auctionapply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionapplyMapper {
    int deleteByPrimaryKey(String applyid);

    int insert(Auctionapply record);

    int insertSelective(Auctionapply record);

    Auctionapply selectByPrimaryKey(String applyid);

    int updateByPrimaryKeySelective(Auctionapply record);

    int updateByPrimaryKey(Auctionapply record);

    int refuseByPrimarykey(String applyid);
}