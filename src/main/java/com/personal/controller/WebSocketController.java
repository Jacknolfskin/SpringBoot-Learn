package com.personal.controller;

import com.personal.entity.dto.ClientMessage;
import com.personal.entity.dto.ServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/3/12 9:22
 * @Path: com.personal.controller
 */
@Controller
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/welcome")//当浏览器向服务器发送请求时，通过@MessageMapping映射此路径。
    @SendTo("/topic/getResponse")//当服务端有消息时对订阅了@SendTo的路径浏览器发送消息
    public ServerMessage say(ClientMessage message) throws InterruptedException{
        Thread.sleep(1000);
        return new ServerMessage("Welcome:" + message.getName() + "!");
    }

    @Scheduled(fixedRate = 5000)
    @SendTo("/topic/hello")
    public void callback() throws Exception {
        //发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/hello", df.format(new Date()));
    }
}
