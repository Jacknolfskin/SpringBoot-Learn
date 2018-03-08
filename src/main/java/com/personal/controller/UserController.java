package com.personal.controller;

import com.personal.dao.UserRepository;
import com.personal.entity.User;
import com.personal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/1/31 13:31
 * @Path: com.personal.controller
 */
@Controller
@Api(value = "UserController", description = "用户相关api")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/find/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="根据id查找用户", notes="获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    public User find(@PathVariable("id") Integer id) {
        logger.info("访问Find方法");
        //User user = userRepository.findUser("vip");
        User user = userService.findById(id);
        return user;
    }

    /**
     * 获取所有用户详细信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    @ApiOperation(value="获取所有用户", notes="获取所有用户详细信息")
    public String findList(Model model) {
        List<User> users = userService.list();
        model.addAttribute("users", users);
        return "user/list";
    }

    /**
     * 编辑用户信息
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/toEdit",method = RequestMethod.GET)
    @ApiOperation(value="获取所有用户", notes="获取所有用户详细信息")
    public String toEdit(Model model,@RequestParam("id") Long id) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    /**
     * 添加用户信息
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    public String add()
    {
        User userEntity = new User();
        userEntity.setUsername("测试");
        userService.saveUser(userEntity);
        return "用户信息添加成功";
    }

    /**
     * 删除用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation(value="删除用户", notes="根据userId删除用户")
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "Long")
    public String delete(@RequestParam("userId") Long userId)
    {
        userRepository.delete(userId);
        return "redirect:/list";
    }

    /**
     * 自定义删除
     * @return
     */
    @RequestMapping(value = "/deleteQuery",method = RequestMethod.POST)
    @ApiOperation(value="删除用户", notes="根据id删除用户")
    @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "userId")
    public String deleteQuery()
    {
        userRepository.deleteQuery("测试");
        return "自定义删除成功";
    }

    /**
     * 根据年龄查找用户
     * @param age
     * @return
     */
    @RequestMapping(value = "/age",method = RequestMethod.POST)
    @ApiOperation(value="根据用户年龄查找用户", notes="根据年龄查找用户")
    @ApiImplicitParam(name = "age", value = "age", required = true, dataType = "Long")
    public List<User> age(@RequestParam("age")String age){

        return userRepository.nativeQuery(20);
    }

    /**
     * 分页查询测试
     * @param page 传入页码，从1开始
     * @return
     */
    @RequestMapping(value = "/cutpage",method = RequestMethod.POST)
    @ApiOperation(value="分页", notes="查找分页")
    @ApiImplicitParam(name = "age", value = "age", required = true, dataType = "int")
    public List<User> cutPage(int page)
    {
        User user = new User();
        user.setSize(5);
        user.setSord("desc");
        user.setPage(page);

        //获取排序对象
        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(user.getSord()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        //设置排序对象参数
        Sort sort = new Sort(sort_direction, user.getSidx());
        //创建分页对象
        PageRequest pageRequest = new PageRequest(user.getPage() - 1,user.getSize(),sort);
        //执行分页查询
        return userRepository.findAll(pageRequest).getContent();
    }
}