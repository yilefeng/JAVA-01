package com.yilefeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Account implements Serializable {

    private long user_id;

    private BigDecimal usd;

    private BigDecimal cny;

}
