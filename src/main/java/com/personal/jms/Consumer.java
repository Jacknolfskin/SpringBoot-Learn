package com.personal.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/23 10:17
 * @Path: com.personal.jms
 */
@Component
public class Consumer {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "sample.queue")
    //该方法会异步执行，也就是说主线程会直接跳过该方法，而是使用线程池中的线程来执行该方法
    @Async
    public void receiveQueue(String text) {
        System.out.println(Thread.currentThread().getName()+"Consumer收到的报文为:"+text);
    }

    @JmsListener(destination = "sample.topic")
    public void receiveTopic(String text) {
        System.out.println(Thread.currentThread().getName()+" Consumer-topic="+text);
    }
}
