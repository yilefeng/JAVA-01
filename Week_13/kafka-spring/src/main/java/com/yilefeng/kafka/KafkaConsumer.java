package com.yilefeng.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(id = "consumer-id", topics = "test-topic")
    public void listen(String msg) {
        System.out.println("listen : " + msg);
    }
}
