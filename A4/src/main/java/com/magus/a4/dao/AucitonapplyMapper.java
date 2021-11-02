package com.magus.a4.dao;

import com.magus.a4.pojo.Aucitonapply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AucitonapplyMapper {
    int deleteByPrimaryKey(String applyid);

    int insert(Aucitonapply record);

    int insertSelective(Aucitonapply record);

    Aucitonapply selectByPrimaryKey(String applyid);

    int updateByPrimaryKeySelective(Aucitonapply record);

    int updateByPrimaryKey(Aucitonapply record);
}