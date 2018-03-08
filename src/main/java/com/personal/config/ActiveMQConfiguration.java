package com.personal.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/23 14:44
 * @Path: com.personal.config
 */
@Configuration
@EnableScheduling
public class ActiveMQConfiguration {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("sample.topic");
    }
}
