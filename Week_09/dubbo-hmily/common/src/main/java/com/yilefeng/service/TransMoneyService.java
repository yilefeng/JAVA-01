package com.yilefeng.service;


import com.yilefeng.entity.Account;
import org.dromara.hmily.annotation.Hmily;

public interface TransMoneyService {

    Account findAccountByUserId(long userId);

    @Hmily
    Boolean transMoney(long userId, Account trans);
}
