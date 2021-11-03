package com.magus.a4.dao;

import com.magus.a4.pojo.Enrollment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentMapper {
    int deleteByPrimaryKey(String enrolluid);

    int insert(Enrollment record);

    int insertSelective(Enrollment record);

    Enrollment selectByPrimaryKey(String enrolluid);

    int updateByPrimaryKeySelective(Enrollment record);

    int updateByPrimaryKey(Enrollment record);

    Enrollment getByAuctionidAndUsername(@Param("auctionid") String auctionid,
                                         @Param("username") String username);
}