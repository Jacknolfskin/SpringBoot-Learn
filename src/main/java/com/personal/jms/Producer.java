package com.personal.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/23 10:18
 * @Path: com.personal.jms
 */
//@Service("producer") //开启Active时打开注解，否则一直重试连接
public class Producer {

    /**
     * 注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
     */
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }

    @Scheduled(fixedDelay=3000)
    public void send() {
        //send queue.
        this.jmsTemplate.convertAndSend(this.queue, "hi,activeMQ");
        //send topic.
        this.jmsTemplate.convertAndSend(this.topic, "hi,activeMQ(topic)");
    }

    @JmsListener(destination="out.queue")
    public void consumerMessage(String text){
        System.out.println("从out.queue队列收到的回复报文为:"+text);
    }
}
