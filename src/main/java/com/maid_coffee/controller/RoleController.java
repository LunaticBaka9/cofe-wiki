package com.maid_coffee.controller;

import com.github.pagehelper.PageInfo;
import com.maid_coffee.common.Result;
import com.maid_coffee.entity.Role;
import com.maid_coffee.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    
    @Resource
    RoleService roleService;

    @GetMapping("/selectAllRoles")
    public Result selectAllRoles() {
        List<Role> roleList = roleService.selectAllRoles();
        return Result.success(roleList);
    }
    
    @GetMapping("/selectPage")
    public Result selectPage(
            @RequestParam(defaultValue="1") Integer pageNum,
            @RequestParam(defaultValue="10") Integer pageSize, 
            Role role){
        PageInfo<Role> pageInfo = roleService.selectPage(pageNum, pageSize, role);
        return Result.success(pageInfo);
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Integer roleId){
        Role role = roleService.selectById(roleId);
        return Result.success(role);
    }
    
    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        roleService.add(role);
        return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Role role){
        roleService.update(role);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer roleId){
        roleService.delete(roleId);
        return Result.success();
    }
}
