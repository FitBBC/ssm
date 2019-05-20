package com.bbc.mybatis.mappers;

import com.bbc.mybatis.po.Order;

public interface OrderMapper {

    Order selectByPrimaryKey(Integer id);
}