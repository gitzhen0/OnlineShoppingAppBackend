package com.zhensmallgroup.mall.model.dao;

import com.github.pagehelper.PageInfo;
import com.zhensmallgroup.mall.model.pojo.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    PageInfo updateByPrimaryKey(OrderItem record);
}