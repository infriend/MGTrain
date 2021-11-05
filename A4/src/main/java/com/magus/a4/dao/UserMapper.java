package com.magus.a4.dao;

import com.magus.a4.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateStatusByUsername(@Param("status") short status,
                               @Param("username") String username);

    int resetPasswdByUsername(@Param("username") String username,
                              @Param("passwd") String passwd);

    List<User> getActiveUserList();
}