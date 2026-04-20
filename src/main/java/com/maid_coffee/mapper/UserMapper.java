package com.maid_coffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.maid_coffee.entity.User;

@Mapper
public interface UserMapper {
    
    List<User> selectAllUsers(User user);

    void insert(User user);

    User selectByUsername(String username);

    void updateByUserId(User user);

    void deleteByUserId(User user);
}
