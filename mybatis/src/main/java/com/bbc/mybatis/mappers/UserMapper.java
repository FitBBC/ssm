package com.bbc.mybatis.mappers;

import com.bbc.mybatis.po.User;

import java.util.List;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    User selectByPrimaryKeyJoinOrder(Integer id);

    List<User> selectList(List<Integer> ids);

    void insertUser(User user);
}