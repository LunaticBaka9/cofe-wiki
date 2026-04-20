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
import com.maid_coffee.entity.User;
import com.maid_coffee.service.UserService;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {
    
    @Resource
    UserService userService;

    @GetMapping("/selectAllUsers")  //完整的请求路径 /user/selectAllUsers
    public Result selectAllUsers() {
        List<User> userList = userService.selectAllUsers();
        return Result.success(userList);
    }
    
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue="1") Integer pageNum,
                             @RequestParam(defaultValue="10") Integer pageSize, 
                             User user){
        PageInfo<User> pageInfo = userService.selectPage(pageNum, pageSize, user);
        return Result.success(pageInfo);
    }
    
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        
        userService.add(user);
        return Result.success();
    }
    
    @PutMapping("/update")
    public Result update(@RequestBody User user){

        userService.update(user);
        return Result.success();
    }

    @PutMapping("/delete")
    public Result delete(@RequestBody User user){
        
        userService.deleteByUserId(user);
        return Result.success();
    }

    @PutMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<User> list){
        
        userService.deleteBatch(list);
        return Result.success();
    }
    
}

