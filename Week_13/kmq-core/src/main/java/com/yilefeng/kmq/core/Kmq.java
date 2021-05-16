package com.yilefeng.kmq.core;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq {

    private String topic;

    private int capacity;

    private KmqMessage[] queue;

    private int atIndex;

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new KmqMessage[capacity];
    }

    public boolean send(KmqMessage message,int index) {
        if (index < capacity && index >= 0 && queue[index] == null) {
            queue[index] = message;
            atIndex = index;
            return true;
        } else return false;
    }

    public KmqMessage poll(long timeout, int index) throws InterruptedException {
        Thread.sleep(timeout);
        if (index < atIndex && index >= 0) {
            return queue[index];
        } else {
            return null;
        }
    }

//    @SneakyThrows
//    public KmqMessage poll(long timeout) {
//        return queue.poll(timeout, TimeUnit.MILLISECONDS);
//    }

}
