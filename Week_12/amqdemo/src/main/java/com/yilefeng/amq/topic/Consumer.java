package com.yilefeng.amq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer {
    public static void main(String[] args) throws JMSException {
        // 定义Destination
        Destination destination = new ActiveMQTopic("test.queue");
        // 创建连接和会话
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
        conn.start();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

        final AtomicInteger count = new AtomicInteger(0);
        MessageListener listener = message -> {
            try {
                // 打印所有的消息内容
                // Thread.sleep();
                System.out.println(count.incrementAndGet() + " => receive from " + destination.toString() + ": " + message);
                // message.acknowledge(); // 前面所有未被确认的消息全部都确认。

            } catch (Exception e) {
                e.printStackTrace(); // 不要吞任何这里的异常，
            }
        };
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 绑定消息监听器
        consumer.setMessageListener(listener);

    }
}
