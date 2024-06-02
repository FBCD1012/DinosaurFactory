package com.example.nftmarket.service;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;

public interface Breeding {

    /**
     * @Params:传入参数
     * @Params:恐龙父亲对象，恐龙母亲对象
     * @LogicSetting:初步进行交配时间为3h
     * @Output:恐龙蛋对象
     * */
    DinosaurEgg creatDinosaurEgg(Person person, Dinosaur dinosaurFather, Dinosaur dinosaurMother);

    Dinosaur getTheDinosaurMother(Person person, Integer index);

    Dinosaur getTheDinosaurFather(Person person, Integer index);

    /**
     * @Params:母恐龙对象
     * @LogicSetting:母恐龙在发育一段时间之后是无法进行生育的（1Month）
     * */
    boolean isBreeding(Dinosaur dinosaurMother);
}
