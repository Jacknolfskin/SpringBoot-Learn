package com.personal.dao;

import com.personal.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author hufei
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
@CacheConfig(cacheNames = "user")
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    User findByUsername(String username);


    @Override
    @Cacheable(key = "#p0")
    List<User> findAll();


    /**
     * 自定义查找
     * @param age
     * @return
     */
    @Query(value = "select * from user where age > ?1",nativeQuery = true)
    List<User> nativeQuery(int age);

    /**
     * 自定义查找
     * @param name
     * @return
     */
    @Query("from User u where u.username=:name")
    User findUser(@Param("name") String name);

    /**
     * 根据用户名、密码删除一条数据
     * @param name
     */
    @Modifying
    @Query(value = "delete from user where username = ?1",nativeQuery = true)
    void deleteQuery(String name);


}
