package com.yilefeng.redis.count;

public class CountDemo {

    public static void main(String[] args) throws InterruptedException {
        RedisCounter redisCounter = new RedisCounter();
        redisCounter.increment("inic");
    }
}
