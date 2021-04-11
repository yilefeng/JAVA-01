package com.yilefeng.redis.controller;

import com.yilefeng.redis.count.RedisCounter;
import com.yilefeng.redis.lock.RedisTemplateDemo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RedisController {

    @RequestMapping("/locktest")
    String test() {
        new Thread(this::distributedLock, "T1").start();
        new Thread(this::distributedLock, "T2").start();
        new Thread(this::distributedLock, "T3").start();
        new Thread(this::distributedLock, "T4").start();
        return "success";
    }

    @RequestMapping("/counter")
    String counter() {
        Long counter = counterTest();
        new Thread(this::counterTest, "T1").start();
        new Thread(this::counterTest, "T2").start();
        new Thread(this::counterTest, "T3").start();
        new Thread(this::counterTest, "T4").start();
        return "success: counter=" + counter;
    }

    private void distributedLock() {
        RedisTemplateDemo redisTemplateDemo = new RedisTemplateDemo();
        redisTemplateDemo.lock("happylock",Thread.currentThread().getName());
    }
    private Long counterTest() {
        RedisCounter redisCounter = new RedisCounter();
        Long counter = null;
        try {
            counter = redisCounter.increment("inic");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行，计数器为：" + counter);
        return counter;
    }
}
