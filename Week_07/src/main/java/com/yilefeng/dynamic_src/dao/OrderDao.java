package com.yilefeng.dynamic_src.dao;

import com.yilefeng.dynamic_src.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yilefeng on 2021/3/7.
 */
@Mapper
@Repository
public interface OrderDao {
    @Insert("INSERT INTO `order`(order_id, product_id,user_id, `count`, unit_price, " +
            "real_price,discount_price) VALUES(#{order_id},#{product_id},#{user_id}," +
            "#{count},#{unit_price},#{real_price},#{discount_price})")
    Integer insert(Order order);

    @Select("select * from `order` where id=#{id}")
    @Results({
            @Result (column="id", property="id"),
            @Result(column="order_id", property="order_id"),
            @Result(column="product_id", property="product_id"),
            @Result(column="user_id", property="user_id"),
            @Result(column="count", property="count"),
            @Result(column="unit_price", property="unit_price"),
            @Result(column="real_price", property="real_price"),
            @Result(column="discount_price", property="discount_price")
    })
    Order query(Long id);
}
