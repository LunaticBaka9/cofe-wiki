package com.maid_coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maid_coffee.entity.User;
import com.maid_coffee.exception.CustomerException;
import com.maid_coffee.mapper.UserMapper;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;

@Service
public class UserService {
    
    @Resource
    UserMapper userMapper;

    public List<User> selectAllUsers(){
        return userMapper.selectAllUsers(null);
    }

    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, User user){
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.selectAllUsers(user);
        return PageInfo.of(list);
    }

    public void add(User user){
        //用户名查重
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser != null){
            throw new CustomerException("账号重复");
        }
        //默认密码
        if(StrUtil.isBlank(user.getPassword())){
            user.setPassword("u123123");
        }
        if(StrUtil.isBlank(user.getUserType())){
            user.setUserType("user");
        }
        if(StrUtil.isBlank(user.getName())){
            user.setName(user.getUsername());
        }
        userMapper.insert(user);
    }

    public void update(User user){
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser != null){
            throw new CustomerException("用户名重复");
        }
        userMapper.updateByUserId(user);
    }

    public void deleteByUserId(User user){
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser == null){
            throw new CustomerException("找不到用户，无法删除");
        }
        userMapper.deleteByUserId(user);
    }

    public void deleteBatch(List<User> list){
        for(User user: list){
            this.deleteByUserId(user);
        }
    }

    public User login(User user){
        //验证账号是否存在
        User dbuser = userMapper.selectByUsername(user.getUsername());
        if(dbuser == null){
            throw new CustomerException("账号或密码错误");
        }
        if(!dbuser.getPassword().equals(user.getPassword())){
            throw new CustomerException("账号或密码错误");
        }
        return dbuser;
    }

    public void register(User user){
        this.add(user);
    }
}
