package com.yilefeng.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by yilefeng on 2021/3/14.
 */
@Data
@TableName("t_order")
public class Order extends Model<Order> {

    private Long orderId;

    private Long userId;

    private BigDecimal amount ;

    private Integer status;
}
