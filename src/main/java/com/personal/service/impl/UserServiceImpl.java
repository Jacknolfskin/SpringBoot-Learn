package com.personal.service.impl;

import com.personal.dao.UserRepository;
import com.personal.entity.User;
import com.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
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
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * allEntries = true: 清空user里的所有缓存
     * @CacheEvict(cacheNames="user", allEntries=true)
     *
     * 对符合key条件的记录从缓存中book1移除
     * @CacheEvict(cacheNames="book1", key="#id")
     *
     * 每次执行都会执行方法，无论缓存里是否有值，同时使用新的返回值的替换缓存中的值
     * @CachePut(cacheNames="book1", key="#id")
     */

    @Override
    public List<User> list()
    {
        System.out.println("进入后台");
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer Id){
        return userRepository.findOne(Long.valueOf(Id));
    }
}
