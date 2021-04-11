package com.yilefeng.redis.subpub;

import redis.clients.jedis.JedisPool;

public class TestMain {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool();
        String channelName = "ORDER";

        SubscribeOrder subscribeOrder = new SubscribeOrder(jedisPool, channelName);
        PublishOrder publishOrder = new PublishOrder(jedisPool, channelName);
    }
}
