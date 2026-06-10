package com.maid_coffee.mapper;

import com.maid_coffee.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> selectAllRoles(Role role);

    Role selectById(Integer roleId);

    Role selectByRoleCode(String roleCode);

    void insert(Role role);

    void update(Role role);

    void delete(Integer roleId);
}
