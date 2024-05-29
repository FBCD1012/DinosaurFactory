package com.example.nftmarket.service;


import com.example.nftmarket.entity.Dinosaur;

//构建恐龙稀有度操作进行理解
public interface DinosaurRarity {

    //传入恐龙对象然后根据恐龙对象的属性来设定相关的稀有度操作
    Dinosaur giveDinosaurRarity(Dinosaur dinosaur);

    //根据上面的稀有度定价
    Integer giveDinosaurPrice(Dinosaur dinosaur);

}
