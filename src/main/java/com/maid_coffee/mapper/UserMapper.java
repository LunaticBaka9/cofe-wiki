package com.maid_coffee.mapper;

import com.maid_coffee.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    
    List<User> selectAllUsers(User user);

    void insert(User user);

    User selectByUsername(String username);

    void updateByUserId(User user);

    void deleteByUserId(User user);
}
