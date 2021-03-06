package com.yilefeng.dynamic_src.annotation;

import java.lang.annotation.*;

/**
 * Created by yilefeng on 2021/3/7.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default DataSource.master;

    String master = "master";

    String slave = "slave";
}
