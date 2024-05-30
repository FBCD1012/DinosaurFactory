package com.example.nftmarket.service;


import com.example.nftmarket.utils.DinosaurDetails;
import com.example.nftmarket.utils.RarityUtils;

//构建恐龙稀有度操作进行理解
public interface DinosaurRarity {

    //传入恐龙对象然后根据恐龙对象的属性来设定相关的稀有度操作
    RarityUtils.Rarities giveDinosaurRarity(DinosaurDetails.DinosaurType dinosaurType, DinosaurDetails.Clothing Clothing, DinosaurDetails.Color color);

    //根据上面的稀有度定价
    Double giveDinosaurPrice(RarityUtils.Rarities rarities,DinosaurDetails.Color dinosaurColor);

}
