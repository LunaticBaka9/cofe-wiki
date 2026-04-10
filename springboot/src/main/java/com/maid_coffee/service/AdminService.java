package com.maid_coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maid_coffee.entity.Admin;
import com.maid_coffee.exception.CustomerException;
import com.maid_coffee.mapper.AdminMapper;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;


@Service
public class AdminService {
    
    @Resource
    AdminMapper adminMapper;

    public List<Admin> selectAllAdmins(){
        return adminMapper.selectAllAdmins(null);
    }

    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin){
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> list = adminMapper.selectAllAdmins(admin);
        return PageInfo.of(list);
    }

    public void add(Admin admin){
        //用户名查重
        Admin dbUser = adminMapper.selectByUsername(admin.getUsername());
        if(dbUser != null){
            throw new CustomerException("用户名重复");
        }
        //默认密码
        if(StrUtil.isBlank(admin.getPassword())){
            admin.setPassword("u123123");
        }
        admin.setUserType("admin");
        adminMapper.insert(admin);
    }

    public void update(Admin admin){
        adminMapper.updateByUserId(admin);
    }

    public void deleteByUserId(Admin admin){
        adminMapper.deleteByUserId(admin);
    }

    public void deleteBatch(List<Admin> list){
        for(Admin admin: list){
            this.deleteByUserId(admin);
        }
    }

    public Admin login(Admin admin){
        //验证账号是否存在
        Admin dbuser = adminMapper.selectByUsername(admin.getUsername());
        if(dbuser == null){
            throw new CustomerException("账号或密码错误");
        }
        if(!dbuser.getPassword().equals(admin.getPassword())){
            throw new CustomerException("账号或密码错误");
        }
        return dbuser;
    }
}
