package com.maid_coffee.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Shop {
    private Long shopId;
    private String shopName;
    private String slogn;
    private String address;
    private String location;
    private String tel;
    private String openTime;
    private int price;
    private boolean shopusing;
    private String coverPath;
    private String shopImg;
    private String shopType;
    private Date updateTime;
    private String intro;
    private String obs1;
    private String obs2;
    private String obs3;
    private String offical;
    private String twitter;
    private String facebook;
    private String menu;
    private String pmenu;
    private String tags;

}
