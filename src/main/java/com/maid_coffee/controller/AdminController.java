package com.maid_coffee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.maid_coffee.common.Result;
import com.maid_coffee.entity.Admin;
import com.maid_coffee.service.AdminService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Resource
    AdminService adminService;

    @GetMapping("/selectAllAdmins")  //完整的请求路径 /admin/selectAllUsers
    public Result selectAllAdmins() {
        List<Admin> adminsList = adminService.selectAllAdmins();
        return Result.success(adminsList);
    }
    
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue="1") Integer pageNum,
                             @RequestParam(defaultValue="10") Integer pageSize, 
                             Admin admin){
        PageInfo<Admin> pageInfo = adminService.selectPage(pageNum, pageSize, admin);
        return Result.success(pageInfo);
    }
    
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        
        adminService.add(admin);
        return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){

        adminService.update(admin);
        return Result.success();
    }

    @PutMapping("/delete")
    public Result delete(@RequestBody Admin admin){
        
        adminService.deleteByUserId(admin);
        return Result.success();
    }

    @PutMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Admin> list){
        
        adminService.deleteBatch(list);
        return Result.success();
    }
    
}
