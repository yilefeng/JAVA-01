package com.yilefeng.sharding;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.utilities.Assert;

/**
 * Created by yilefeng on 2021/3/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderShardingServiceTest extends TestCase {

    @Autowired
    private OrderShardingService orderShardingService;

    @Test
    public void testAdd() throws Exception {
        Assert.that(orderShardingService.add() == 1, "Insert error");
    }

    @Test
    public void testQuery() throws Exception {
        Assert.that(orderShardingService.query(1l) != null, "query error");
    }
}