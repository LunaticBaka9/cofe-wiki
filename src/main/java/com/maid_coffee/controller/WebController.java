package com.maid_coffee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maid_coffee.common.Result;
import com.maid_coffee.entity.User;
import com.maid_coffee.service.UserService;

import jakarta.annotation.Resource;



@RestController
public class WebController {
    
    @Resource
    UserService userService;
    
    @GetMapping("/")
    public Result hello(){
        return Result.success("Springboot is Running");
    }
    
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User dbuser = userService.login(user);
        return Result.success(dbuser);
    }
    
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.register(user);
        return Result.success();
    }
    
}
