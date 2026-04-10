package com.maid_coffee.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maid_coffee.common.Result;

//全局异常捕获器
@ControllerAdvice("com.maid_coffee.controller")
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
   
    @ExceptionHandler(Exception.class)
    @ResponseBody //将result对象转换成 json格式
    public Result error(Exception e){
        log.error("系统异常", e);
        return Result.error("系统异常");
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseBody //将result对象转换成 json格式
    public Result customerError(CustomerException e){
        log.error("自定义错误", e);
        return Result.error(e.getCode(), e.getMsg());
    }
}
