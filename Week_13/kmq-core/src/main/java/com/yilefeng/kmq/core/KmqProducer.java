package com.yilefeng.kmq.core;

public class KmqProducer {

    private KmqBroker broker;
    private int atIndex;

    public KmqProducer(KmqBroker broker) {
        this.broker = broker;
    }

    public boolean send(String topic, KmqMessage message) {
        Kmq kmq = this.broker.findKmq(topic);
        if (null == kmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        return kmq.send(message,atIndex++);
    }
}
