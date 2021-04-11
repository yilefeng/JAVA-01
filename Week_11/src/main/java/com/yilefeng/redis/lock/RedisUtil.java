package com.yilefeng.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    public static RedisTemplate redis;
    @PostConstruct
    public void getRedisTemplate(){
        redis=this.redisTemplate;
        redis.setValueSerializer(new GenericToStringSerializer(Object.class));
        redis.setKeySerializer(new StringRedisSerializer());
    }
}
