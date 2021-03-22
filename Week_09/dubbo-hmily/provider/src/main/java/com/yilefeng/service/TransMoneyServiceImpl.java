package com.yilefeng.service;

import com.yilefeng.entity.Account;
import com.yilefeng.mapper.AccountMapper;
import com.yilefeng.mapper.FreezeAccountMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by yilefeng on 2021/3/21.
 */
@Service
@DubboService
public class TransMoneyServiceImpl implements TransMoneyService  {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    FreezeAccountMapper freezeAccountMapper;

    @Override
    public Account findAccountByUserId(long userId) {
        return accountMapper.findByUserId(userId);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean transMoney(long userId, Account trans) {
        BigDecimal usd = trans.getUsd();
        BigDecimal cny = trans.getCny();

        boolean result = true;
        if (usd != null) {
            result = accountMapper.subUsd(userId, usd) > 0 && freezeAccountMapper.addUsd(userId, usd) > 0;
        }
        if (cny != null) {
            result = result && accountMapper.subCny(userId, cny) > 0 && freezeAccountMapper.addCny(userId, cny) > 0;
        }
        return result;
    }

    public boolean confirm(long userId, Account trans) {
        return addMoney(userId, trans) && clearFreeze(userId, trans);
    }

    public boolean cancel(long userId, Account trans) {
        return addMoney(userId, trans) && clearFreeze(userId, trans);
    }

    private boolean clearFreeze(long userId, Account trans) {
        BigDecimal usd = trans.getUsd();
        BigDecimal cny = trans.getCny();
        boolean result = true;
        if (usd != null) {
            result = freezeAccountMapper.subUsd(userId, usd) > 0;
        }
        if (cny != null) {
            result = result && freezeAccountMapper.subCny(userId, cny) > 0;
        }
        return result;
    }

    private boolean addMoney(long userId, Account trans) {
        BigDecimal  usd = trans.getUsd();
        BigDecimal cny = trans.getCny();
        boolean result = true;
        if (usd != null) {
            result = accountMapper.addCny(userId, usd.multiply(BigDecimal.valueOf(7))) > 0;
        }
        if (cny != null) {
            result = result && accountMapper.addUsd(userId, cny.divide(BigDecimal.valueOf(7))) > 0;
        }
        return result;
    }
}
