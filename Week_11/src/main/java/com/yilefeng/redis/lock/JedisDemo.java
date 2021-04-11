package com.yilefeng.redis.lock;

import redis.clients.jedis.Jedis;

public class JedisDemo {
    private String host;
    private int port;


    public JedisDemo(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public void execTest(String lockId, String tName) {
        Jedis jedis = new Jedis(host, port);
//        String res = jedis.set(lockId,"1","nx","px",10);
//        if ("OK".equals(res)) {
//            System.out.println(tName + "获得锁");
//        } else {
//            System.out.println(tName + "没有获得锁");
//        }
        Long res = jedis.setnx(lockId, tName);
        if (res == 1) {
            System.out.println(tName + "获得锁");
            jedis.expire(lockId, 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (tName.equals(jedis.get(lockId))) {
                    jedis.del(lockId);
                }
            }
        } else {
            System.out.println(tName + "没有获得锁");
        }
    }




}
