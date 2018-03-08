package com.personal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author: Jacknolfskin
 * @Date: 2018/1/26 10:41
 * @Path: com.personal.dao
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {

}
