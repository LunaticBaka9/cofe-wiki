package com.maid_coffee.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String description;
    private Date createTime;
    private Date updateTime;
}
