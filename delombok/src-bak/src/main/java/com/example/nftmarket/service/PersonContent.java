package com.example.nftmarket.service;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;

import java.util.List;

public interface PersonContent {
    //添加两个龙蛋到我们用户的随机仓库之中
    String addTheDinosaurEgg(Person person);

    //孵化用户当前拥有的恐龙蛋操作
    Dinosaur toHatchTheDinosaurEgg(Person person, Integer index);

    List<DinosaurEgg> getTheDinosaurEgg(Person person);

    int getDinosaurCounts(Person person);
}
