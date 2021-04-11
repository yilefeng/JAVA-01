package com.yilefeng.redis.controller;

import com.yilefeng.redis.subpub.PublishOrder;
import com.yilefeng.redis.subpub.SubscribeOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@EnableAutoConfiguration
public class OrderController {
    @RequestMapping("/subpub")
    String test() {
        JedisPool jedisPool = new JedisPool();
        String channelName = "ORDER";

        SubscribeOrder subscribeOrder = new SubscribeOrder(jedisPool, channelName);
        PublishOrder publishOrder = new PublishOrder(jedisPool, channelName);
        return "success";
    }
}
