package com.personal.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/23 10:17
 * @Path: com.personal.jms
 */
@Component
public class Consumer2 {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "sample.queue")
    @SendTo("out.queue")
    public String receiveQueue(String text) {
        System.out.println(Thread.currentThread().getName()+"Consumer2收到的报文为:"+text);
        return "return message"+text;
    }
}
