package com.magus.a4.dao;

import com.magus.a4.pojo.Enrollment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollmentMapper {
    int deleteByPrimaryKey(String enrolluid);

    int insert(Enrollment record);

    int insertSelective(Enrollment record);

    Enrollment selectByPrimaryKey(String enrolluid);

    int updateByPrimaryKeySelective(Enrollment record);

    int updateByPrimaryKey(Enrollment record);
}