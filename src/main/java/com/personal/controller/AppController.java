package com.personal.controller;

import com.personal.entity.dto.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * app相关的controller，支持跨域
 */
@RequestMapping("/app")
@RestController
@CrossOrigin
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping(value = "/logout")
    public ResultBean<Boolean> logout(HttpSession session) {
        session.invalidate();
        return new ResultBean<Boolean>(true);
    }
}
