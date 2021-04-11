package com.yilefeng.redis.lock;

public class DistributedLock {
    private static final String LOCK_ID = "happylock";

    public static void main(String[] args) throws InterruptedException {
        doSomeThing();
        new Thread(DistributedLock::doSomeThing,"T1").start();
        new Thread(DistributedLock::doSomeThing,"T2").start();
        new Thread(DistributedLock::doSomeThing,"T3").start();
        new Thread(DistributedLock::doSomeThing,"T4").start();
    }

    public static void doSomeThing() {
        JedisDemo jedisDemo = new JedisDemo("localhost",6379);
        jedisDemo.execTest(LOCK_ID,Thread.currentThread().getName());
    }
}
