package com.yilefeng.sharding.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yilefeng.sharding.entity.Order;
import junit.framework.TestCase;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yilefeng on 2021/3/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest extends TestCase {

    @Autowired
    private OrderService orderService;

    @Test
    public void testSave() {
        Order order = new Order();
        order.setUserId(3l);
        order.setAmount(new BigDecimal(7.20));
        order.setStatus(1);
        orderService.save(order);

        Order order2 = new Order();
        order2.setUserId(4l);
        order2.setAmount(new BigDecimal(7.20));
        order2.setStatus(1);
        orderService.save(order2);
    }

    @Test
    public void query() {
        List<Order> list = orderService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void update() {
        Order order = new Order();
        order.setAmount(new BigDecimal(100.20));
        order.setStatus(2);

        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", 577997330779734017l);
        orderService.update(order, updateWrapper);

        query();
    }

    @Test
    public void delete() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id", 577997330779734017l);
        orderService.remove(queryWrapper);
        query();
    }

    @Test
    public void page() {
        Page<Order> page = new Page<>(1, 2);
        QueryWrapper queryWrapper = new QueryWrapper<Order>();
        queryWrapper.orderByAsc("order_id");

        Page<Order> orderPage = orderService.page(page, queryWrapper);
        assertEquals(2, orderPage.getRecords().size());
    }


    /**
     *
     *xa事务 不同库，两个库均插入成功
     */
    @Test
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void  saveTransaction() {

        TransactionTypeHolder.set(TransactionType.XA);

        Order order = new Order();
        order.setUserId(111l);
        order.setAmount(new BigDecimal(3.20));
        order.setStatus(1);
        orderService.save(order);

        Order order2 = new Order();
        order2.setUserId(120l);
        order2.setAmount(new BigDecimal(3.20));
        order2.setStatus(1);
        orderService.save(order2);

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", 111l);
        Order one = orderService.getOne(queryWrapper);
        System.out.print(one);

        QueryWrapper<Order> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", 120l);
        Order one1 = orderService.getOne(queryWrapper1);
        System.out.print(one1);
    }

    /**
     *
     * xa事务 不同库，两个库一个成功一个失败 都会回滚
     */
    @Test
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void  saveTransactionRobback() {
        try {
            Order order = new Order();
            order.setUserId(165l);
            order.setAmount(new BigDecimal(3.20));
            order.setStatus(1);
            orderService.save(order);

            Order order2 = new Order();
            order2.setUserId(166l);
            order2.setAmount(new BigDecimal(3.20));
            order2.setStatus(2000323123);
            orderService.save(order2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}