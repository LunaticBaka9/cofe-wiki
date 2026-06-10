package com.maid_coffee.mapper;

import com.maid_coffee.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {

    List<Shop> selectAllShops(Shop shop);

    Shop selectByShopId(Integer shopId);

    void insert(Shop shop);

    Shop selectByShopName(String shopName);

    void updateByShopId(Shop shop);

    void updateCoverPathByShopId(Long shopId, String coverPath);

    void deleteByShopId(Shop shop);

}
