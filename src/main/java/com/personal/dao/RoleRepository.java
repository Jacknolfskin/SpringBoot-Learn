package com.personal.dao;

import com.personal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Override
    List<Role> findAll();
}
