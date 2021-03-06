package com.yilefeng.sharding;

import com.alibaba.fastjson.JSON;
import com.yilefeng.dynamic_src.annotation.DataSource;
import com.yilefeng.dynamic_src.dao.OrderDao;
import com.yilefeng.dynamic_src.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yilefeng on 2021/3/7.
 */
@Service
public class OrderShardingService {

    @Resource
    private OrderDao orderDao;

    public int add() {
        Order order = new Order();
        order.setOrder_id("order_id1");
        order.setUser_id(1l);
        order.setProduct_id(2l);
        order.setCount(100);
        order.setReal_price(220);
        order.setDiscount_price(300);
        order.setUnit_price(300);
        return orderDao.insert(order);
    }

    public Order query(Long id) {
        Order order = orderDao.query(id);
        System.out.print(JSON.toJSONString(order));
        return order;
    }
}
