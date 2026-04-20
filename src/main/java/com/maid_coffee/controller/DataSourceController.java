package com.maid_coffee.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/datasource")
    public String getDataSourceInfo() {
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            String userName = connection.getMetaData().getUserName();
            return String.format("数据库连接地址: %s, 用户名: %s", url, userName);
        } catch (SQLException e) {
            return "获取数据库连接信息失败: " + e.getMessage();
        }
    }
}