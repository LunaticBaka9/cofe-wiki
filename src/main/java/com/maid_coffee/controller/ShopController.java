package com.maid_coffee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.maid_coffee.common.Result;
import com.maid_coffee.entity.Shop;
import com.maid_coffee.service.ShopService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/shop")
public class ShopController {
    
    @Resource
    ShopService shopService;

    @GetMapping("/selectAllShops")  
    public Result selectAllShops() {
        List<Shop> shopList = shopService.selectAllShops();
        return Result.success(shopList);
    }

    @GetMapping("/selectShopById/{shopId}")
    public Result selectShopById(@PathVariable Integer shopId) {
        Shop shop = shopService.selectByShopId(shopId);
        return Result.success(shop);
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue="1") Integer pageNum,
                             @RequestParam(defaultValue="10") Integer pageSize, 
                             Shop shop){
        PageInfo<Shop> pageInfo = shopService.selectPage(pageNum, pageSize, shop);
        return Result.success(pageInfo);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Shop shop) {
        
        shopService.add(shop);
        return Result.success();
    }
    
     @PutMapping("/update")
    public Result update(@RequestBody Shop shop){

        shopService.update(shop);
        return Result.success();
    }

    @PutMapping("/delete")
    public Result delete(@RequestBody Shop shop){
        
        shopService.deleteByShopId(shop);
        return Result.success();
    }

    @PutMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Shop> list){
        
        shopService.deleteBatch(list);
        return Result.success();
    }
}
