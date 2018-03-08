package com.personal.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 *  资源类：本实例指的是访问路径
 */
@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id",length=10)
    private Integer id;

    @Column(name="permissionName",length=1000)
    private String permissionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}

