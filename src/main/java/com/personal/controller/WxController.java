package com.personal.controller;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/1/31 15:31
 * @Path: com.personal.controller
 */
@Controller
public class WxController {

    @Resource
    private WxMpService wxMpService;

    /**
     * 通过code获得基本用户信息
     *
     * @param response
     * @param code     code
     */
    @RequestMapping(value = "/getOAuth2UserInfo")
    @ResponseBody
    public WxMpUser getOAuth2UserInfo(HttpServletResponse response, @RequestParam(value = "code") String code) {
        WxMpOAuth2AccessToken accessToken;
        WxMpUser wxMpUser = null;
        try {
            accessToken = this.wxMpService.oauth2getAccessToken(code);
            wxMpUser = this.wxMpService.getUserService().userInfo(accessToken.getOpenId());
            return  wxMpUser;
        } catch (WxErrorException e) {
            return wxMpUser;
        }
    }
}
