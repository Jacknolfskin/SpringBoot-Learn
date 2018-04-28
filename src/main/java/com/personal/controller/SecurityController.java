package com.personal.controller;

import com.personal.entity.Msg;
import com.personal.entity.User;
import com.personal.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/1/31 17:31
 * @Path: com.personal.controller
 */
@Controller
@Api(value = "SecurityController", description = "公用安全api")
public class SecurityController {

    @Resource
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    /**
     * 该方法是注册用户的方法，默认放开访问控制
     * @param user
     */
    @PostMapping("/signUp")
    public void signUp(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }

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
