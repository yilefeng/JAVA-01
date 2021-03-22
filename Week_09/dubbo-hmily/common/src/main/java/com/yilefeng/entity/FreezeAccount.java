package com.yilefeng.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class FreezeAccount implements Serializable {

    private long user_id;

    private BigDecimal usd;

    private BigDecimal cny;

}
