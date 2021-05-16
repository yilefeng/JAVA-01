package com.yilefeng;

import com.yilefeng.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;

public class KafkaSpringApplication {
    @Autowired
    private KafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringApplication.class, args);
    }

    public void run(ApplicationArguments args) {
        producer.produce();
    }
}
