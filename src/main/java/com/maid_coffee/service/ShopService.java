package com.maid_coffee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maid_coffee.entity.Shop;
import com.maid_coffee.exception.CustomerException;
import com.maid_coffee.mapper.ShopMapper;

import jakarta.annotation.Resource;

@Service
public class ShopService {

    @Resource
    ShopMapper shopMapper;

    public List<Shop> selectAllShops(){
        return shopMapper.selectAllShops(null);
    }

    public Shop selectByShopId(Integer shopId){
        return shopMapper.selectByShopId(shopId);
    }

    public PageInfo<Shop> selectPage(Integer pageNum, Integer pageSize, Shop shop){
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        List<Shop> list = shopMapper.selectAllShops(shop);
        return PageInfo.of(list);
    }

    public void add(Shop shop){
        Shop dbShop = shopMapper.selectByShopName(shop.getShopName());
        if(dbShop != null){
            System.out.println("Duplicate shop name: " + dbShop.getShopName());
            throw new CustomerException("商店名称重复");
        }
        shopMapper.insert(shop);
    }

    public void update(Shop shop){
        shopMapper.updateByShopId(shop);
    }

    public void updateCoverPath(Integer shopId, String coverPath){
        shopMapper.updateCoverPathByShopId(shopId, coverPath);
    }

    public void deleteByShopId(Shop shop){
        Shop dbShop = shopMapper.selectByShopName(shop.getShopName());
        if(dbShop == null){
            throw new CustomerException("找不到用户，无法删除");
        }
        shopMapper.deleteByShopId(shop);
    }

    public void deleteBatch(List<Shop> list){
        for(Shop shop: list){
            this.deleteByShopId(shop);
        }
    }
}
