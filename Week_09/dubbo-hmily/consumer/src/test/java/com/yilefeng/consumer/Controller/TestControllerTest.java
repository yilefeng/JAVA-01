package com.yilefeng.consumer.Controller;

import com.yilefeng.service.TransMoneyService;
import junit.framework.TestCase;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yilefeng on 2021/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTest extends TestCase {

    @DubboReference
    private TransMoneyService transMoneyService;

    public void testFindAccountByUserId() throws Exception {

    }

    public void testTransUsd() throws Exception {

    }

    public void testTransCny() throws Exception {

    }
}