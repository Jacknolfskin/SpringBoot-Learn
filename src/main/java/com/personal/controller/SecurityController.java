package com.personal.controller;

import com.personal.entity.Msg;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/1/31 17:31
 * @Path: com.personal.controller
 */
@Controller
@Api(value = "SecurityController", description = "公用安全api")
public class SecurityController {


    @RequestMapping("/login")
    public String login() { return "security/login"; }

    /**
     * 初始化上传文件界面，跳转到index.jsp
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index"; }

    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("权限主页面", "主页面", "额外信息,只对管理员显示");
        model.addAttribute("msg", msg);
        return "security/index";
    }
}
