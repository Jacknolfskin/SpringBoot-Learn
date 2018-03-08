package com.personal.dao;

import com.personal.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {

    /**
     *  通过角色名称获取权限列表
     * @param rolename
     * @return
     */
    @Query(value = "SELECT * FROM permission  WHERE id IN ( SELECT permission_id FROM permission_role  WHERE role_id = ( SELECT  id  FROM role  WHERE role_name = ?1))",nativeQuery = true)
    List<Permission> findByRoleName(String rolename);
}
