package com.example.nftmarket.service.impl;

import com.example.nftmarket.service.DinosaurRarity;
import com.example.nftmarket.utils.DinosaurDetails;
import com.example.nftmarket.utils.PriceUtils;
import com.example.nftmarket.utils.RarityUtils;
import org.springframework.stereotype.Service;


/**
 * @INFO:构建接口实现类，方便相关参数进行调用操作
 * */
@Service
public class DinosaurRarityImpl implements DinosaurRarity {
    //进一步封装参数的传递操作
    @Override
    public  RarityUtils.Rarities giveDinosaurRarity(DinosaurDetails.DinosaurType dinosaurType,DinosaurDetails.Clothing Clothing,DinosaurDetails.Color color){
        return RarityUtils.getTheRarity(dinosaurType, Clothing, color);
    }

    @Override
    public Double giveDinosaurPrice(RarityUtils.Rarities rarities,DinosaurDetails.Color color) {
        return PriceUtils.getThePrice(rarities, color);
    }
}
