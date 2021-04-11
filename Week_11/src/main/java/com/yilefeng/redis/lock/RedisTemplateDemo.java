package com.yilefeng.redis.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class RedisTemplateDemo {
    /*** 分布式锁固定前缀 ***/
    private static final String REDIS_LOCK = "redis_lock_";
    /*** 分布式锁过期时间 ***/
    private static final Integer EXPIRE_TIME = 30;
    /*** 每次自旋睡眠时间 ***/
    private static final Integer SLEEP_TIME = 50;
    /*** 分布式锁自旋次数 ***/
    private static final Integer CYCLES = 10;
    //    @SuppressWarnings("all")
//    @Resource(name = "redisTemplate")
//    private ValueOperations<String, String> lockOperations;
//    @Autowired
    private RedisTemplate redisTemplate;

    public RedisTemplateDemo() {
        this.redisTemplate = RedisUtil.redis;
    }

    public void lock(String key, String value) {
        lock(key, value, EXPIRE_TIME);
    }

    public void lock(String key, String value, Integer timeout) {
        Assert.isTrue(StringUtils.isEmpty(key), "count locks are identified as null.");
        Assert.isTrue(StringUtils.isEmpty(value), "the count release lock is identified as null.");
        int cycles = CYCLES;
        // ----- 尝试获取锁，当获取到锁，则直接返回，否则，循环尝试获取
        while (!tryLock(key, value, timeout)) {
            // ----- 最多循环10次，当尝试了10次都没有获取到锁，抛出异常
            if (0 == (cycles--)) {
//                log.error("count try lock fail. key: {}, value: {}", key, value);
                throw new RuntimeException(value + "count try lock fail.");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
            } catch (Exception e) {
//                log.error("history try lock error.", e);
            }
        }
    }

    private boolean tryLock(String key, String value, Integer timeout) {
//        Boolean result = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK + key, value, timeout, TimeUnit.SECONDS);
        Boolean result = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK + key, value);
        if (result) {
            redisTemplate.expire(REDIS_LOCK + key, timeout, TimeUnit.SECONDS);
        }
        return result != null && result;
    }

    public void unLock(String key, String value) {
        Assert.isTrue(StringUtils.isEmpty(key), "count locks are identified as null.");
        Assert.isTrue(StringUtils.isEmpty(value), "the count release lock is identified as null.");
        key = REDIS_LOCK + key;
        // ----- 通过value判断是否是该锁：是则释放；不是则不释放，避免误删
        if (value.equals(redisTemplate.opsForValue().get(key))) {
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }
}
