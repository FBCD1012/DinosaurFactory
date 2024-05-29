package com.example.nftmarket.service;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurAgg;

public interface Breeding {

    /**
     * @Params:传入参数
     * @Params:恐龙父亲对象，恐龙母亲对象
     * @LogicSetting:初步进行交配时间为3h
     * @Output:恐龙蛋对象
     * */
    DinosaurAgg creatDinosaurAgg(Dinosaur dinosaurFather,Dinosaur dinosaurMother);



    /**
     * @Params:母恐龙对象
     * @LogicSetting:母恐龙在发育一段时间之后是无法进行生育的（1Month）
     * */
    boolean isBreeding(Dinosaur dinosaurMother);
}
