package com.personal.entity;

import lombok.Data;

import javax.persistence.*;

/**
 *  资源与角色中间表
 */
@Entity
@Data
public class PermissionRole {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private int id;

    @Column(name="roleId",length=50)
    private String roleId;

    @Column(name="permissionId",length=50)
    private String permissionId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
