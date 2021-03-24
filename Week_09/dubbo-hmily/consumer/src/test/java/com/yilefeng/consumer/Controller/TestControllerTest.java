package com.yilefeng.consumer.Controller;

import com.google.gson.Gson;
import com.yilefeng.entity.Account;
import com.yilefeng.service.TransMoneyService;
import junit.framework.TestCase;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by yilefeng on 2021/3/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTest extends TestCase {

    @DubboReference
    private TransMoneyService transMoneyService;

    @Test
    public void testFindAccountByUserId() throws Exception {
        Gson gson = new Gson();
        System.out.println(gson.toJson(transMoneyService.findAccountByUserId(1L)));
        System.out.println(gson.toJson(transMoneyService.findAccountByUserId(2L)));
    }

    @Test
    public void testTransUsd() throws Exception {
        //用户A 转1美元
        Account account = new Account();
        account.setUser_id(1L);
        account.setUsd(new BigDecimal(1));
        Boolean aBoolean = transMoneyService.transMoney(1L, account);
        System.out.println("Tran usd " + aBoolean);

        testFindAccountByUserId();
    }

    @Test
    public void testTransCny() throws Exception {
        //用户B 转人民币7元
        Account account = new Account();
        account.setUser_id(2L);
        account.setCny(new BigDecimal(7));
        Boolean aBoolean = transMoneyService.transMoney(2L, account);
        System.out.println("Tran usd " + aBoolean);

        testFindAccountByUserId();
    }
}