package com.personal;

import com.personal.jms.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/23 10:25
 * @Path: PACKAGE_NAME
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
//@EnableAsync // 开启异步任务支持
public class SpringbootJmsApplicationTests {

    @Autowired
    private Producer producer;

    private static int count = 0;

    @Test
    public void contextLoads() throws InterruptedException {
        // 这里定义了Queue的key
        //Destination destination = new ActiveMQQueue("mytest.queue");

        /*for(int i=0; i<100; i++){
            producer.sendMessage(destination, "myname is chhliu!!!");
        }*/
        /*String message = "Send AMQ Test ..." + count;
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "]" + message);
        count++;*/
        //producer.sendMessage(destination, "myname is chhliu!!!");
        //producer.send();
    }
}
