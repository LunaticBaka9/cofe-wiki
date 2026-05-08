package com.maid_coffee.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Integer userId;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String userType;
    private Date createTime;
    private Date deleteTime;
    private boolean userusing;

}
