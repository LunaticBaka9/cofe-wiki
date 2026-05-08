package com.maid_coffee.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maid_coffee.entity.Role;
import com.maid_coffee.exception.CustomerException;
import com.maid_coffee.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;

    public List<Role> selectAllRoles(){
        return roleMapper.selectAllRoles(null);
    }

    public PageInfo<Role> selectPage(Integer pageNum, Integer pageSize, Role role){
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectAllRoles(role);
        return PageInfo.of(list);
    }

    public Role selectById(Integer roleId){
        Role role = roleMapper.selectById(roleId);
        if(role == null){
            throw new CustomerException("角色不存在");
        }
        return role;
    }

    public Role selectByRoleCode(String roleCode){
        return roleMapper.selectByRoleCode(roleCode);
    }

    public void add(Role role){
        Role dbRole = roleMapper.selectByRoleCode(role.getRoleCode());
        if(dbRole != null){
            throw new CustomerException("角色代码已存在");
        }
        roleMapper.insert(role);
    }

    public void update(Role role){
        Role existRole = roleMapper.selectById(role.getRoleId());
        if(existRole == null){
            throw new CustomerException("角色不存在");
        }
        roleMapper.update(role);
    }

    public void delete(Integer roleId){
        Role existRole = roleMapper.selectById(roleId);
        if(existRole == null){
            throw new CustomerException("角色不存在");
        }
        roleMapper.delete(roleId);
    }
}
