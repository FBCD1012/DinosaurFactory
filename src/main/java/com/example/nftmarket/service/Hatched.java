package com.example.nftmarket.service;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurAgg;

public interface Hatched {
    //判定恐龙是否能进行孵化操作
    boolean isHatched(DinosaurAgg dinosaurAgg);
    //执行孵化操作,返回值，返回龙对象
    Dinosaur toHatch(DinosaurAgg dinosaurAgg);
}
