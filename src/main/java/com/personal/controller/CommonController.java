package com.personal.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.personal.utils.gifCode.Captcha;
import com.personal.utils.gifCode.GifCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/2/6 16:16
 * @Path: com.personal.controller
 * 验证码实现类
 */
@Controller
public class CommonController {

    private final static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * spring session
     * @param request
     * @return
     */
    @GetMapping("/session")
    @ResponseBody
    public String uid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return uid.toString() + " : " + session.getId();
    }

    /**
     * 获取静态验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/getKaptchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //将验证码存到session
        //session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        logger.info(capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @GetMapping(value="/getGifCode")
    public void getGifCode(HttpServletResponse response,HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,42,4);
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();
            System.out.println( captcha.text().toLowerCase());
        } catch (Exception e) {
            logger.error("获取验证码异常：%s",e.getMessage());
        }
    }


    /**
     * 发送邮件
     * @throws Exception
     */
    @GetMapping("/sendMail")
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1352490133@qq.com");
        message.setTo("dyc87112@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }
}
