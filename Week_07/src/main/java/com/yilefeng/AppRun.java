package com.yilefeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by yilefeng on 2021/3/7.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"com.yilefeng.dynamic_src.dao"})
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }

}
