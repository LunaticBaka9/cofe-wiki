package com.maid_coffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.maid_coffee.entity.Shop;

@Mapper
public interface ShopMapper {
    
    List<Shop> selectAllShops(Shop shop);

    Shop selectByShopId(Integer shopId);

    void insert(Shop shop);

    Shop selectByShopName(String shopName);

    void updateByShopId(Shop shop);

    void updateCoverPathByShopId(Integer shopId, String coverPath);

    void deleteByShopId(Shop shop);
   
}
