package com.personal.service;

import com.personal.dao.UserRepository;
import com.personal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：恒宇少年
 * Date：2017/4/17
 * Time：22:43
 * 码云：http://git.oschina.net/jnyqy
 * ========================
 */
public interface UserService {

    /**
     * 查询所有用户
     * @return
     */
    List<User> list();

    /**
     * 根据用户Id查找
     * @param Id
     * @return
     */
    User findById(Integer Id);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);
}
