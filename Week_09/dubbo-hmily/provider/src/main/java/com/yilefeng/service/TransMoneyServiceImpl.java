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
public class TransMoneyServiceImpl implements TransMoneyService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    FreezeAccountMapper freezeAccountMapper;

    @Override
    public Account findAccountByUserId(long userId) {
        return accountMapper.findByUserId(userId);
    }

    /**
     * 减少转出金额，增加冻结金额
     *
     * @param userId
     * @param trans
     * @return
     */
    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancelTrans")
    public Boolean transMoney(long userId, Account trans) {
        System.out.println("第一阶段提交,减少转出金额，增加冻结金额");
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

    /**
     * 增加转换币种金额；释放冻结金额
     *
     * @param userId
     * @param trans
     * @return
     */
    public boolean confirm(long userId, Account trans) {
        System.out.println("第二阶段提交，增加转换币种金额；释放冻结金额");
        BigDecimal usd = trans.getUsd();
        BigDecimal cny = trans.getCny();
        boolean result = true;
        if (usd != null) {
            result = accountMapper.addCny(userId, usd.multiply(BigDecimal.valueOf(7))) > 0 &&
                    freezeAccountMapper.subUsd(userId, usd) > 0;
        }
        if (cny != null) {
            result = result && accountMapper.addUsd(userId, cny.divide(BigDecimal.valueOf(7))) > 0 &&
                    freezeAccountMapper.subCny(userId, cny) > 0;
        }
        return result;
    }

    /**
     * 增加转出金额，释放冻结
     *
     * @param userId
     * @param trans
     * @return
     */
    public boolean cancelTrans(long userId, Account trans) {
        System.out.println("回滚,增加转出金额，释放冻结");
        BigDecimal usd = trans.getUsd();
        BigDecimal cny = trans.getCny();
        boolean result = true;
        if (usd != null) {
            result = accountMapper.addUsd(userId, usd) > 0 && freezeAccountMapper.subUsd(userId, usd) > 0;
        }

        if (cny != null) {
            result = accountMapper.addCny(userId, cny) > 0 && freezeAccountMapper.subCny(userId, cny) > 0;
        }
        return result;
    }
}
