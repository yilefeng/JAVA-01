package com.yilefeng.mapper;

import com.yilefeng.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * Created by yilefeng on 2021/3/21.
 */
public interface AccountMapper {
    @Select("select * from account where user_id = #{userId}")
    Account findByUserId(long userId);

    @Update("update account set usd = usd - #{money} where user_id = #{userId}")
    int subUsd(@Param("userId") long userId, @Param("money") BigDecimal money);

    @Update("update account set usd = usd + #{money} where user_id = #{userId}")
    int addUsd(@Param("userId") long userId, @Param("money") BigDecimal money);

    @Update("update account set cny = cny - #{money} where user_id = #{userId}")
    int subCny(@Param("userId") long userId, @Param("money") BigDecimal money);

    @Update("update account set cny = cny + #{money} where user_id = #{userId}")
    int addCny(@Param("userId") long userId, @Param("money") BigDecimal money);
}
