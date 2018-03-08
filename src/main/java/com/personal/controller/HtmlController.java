package com.personal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/1/31 17:31
 * @Path: com.personal.controller
 */
@Controller
@Api(value = "HtmlController", description = "Html模板渲染相关api")
public class HtmlController {

    /**
     * 测试hello
     *
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiIgnore
    public String hello(Model model) throws Exception {
        try {
            model.addAttribute("name", "Dear");
            return "hell";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 测试html引擎
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/helloHtml", method = RequestMethod.GET)
    @ApiOperation(value = "测试html引擎")
    @ApiImplicitParam(name = "model", value = "Model", required = true, dataType = "Model")
    public String helloHtml(Model model) {
        model.addAttribute("host", "from TemplateController.helloHtml");
        return "helloHtml";
    }
}
