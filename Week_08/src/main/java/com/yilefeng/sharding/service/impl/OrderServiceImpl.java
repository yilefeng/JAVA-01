package com.yilefeng.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yilefeng.sharding.dao.OrderMapper;
import com.yilefeng.sharding.entity.Order;
import com.yilefeng.sharding.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yilefeng on 2021/3/14.
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
