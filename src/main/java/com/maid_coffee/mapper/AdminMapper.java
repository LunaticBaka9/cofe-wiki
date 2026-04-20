package com.maid_coffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.maid_coffee.entity.Admin;

@Mapper
public interface AdminMapper {

    List<Admin> selectAllAdmins(Admin admin);

    void insert(Admin admin);

    Admin selectByUsername(String username);

    void updateByUserId(Admin admin);

    void deleteByUserId(Admin admin);

}