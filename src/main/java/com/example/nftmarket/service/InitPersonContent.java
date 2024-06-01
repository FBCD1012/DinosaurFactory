package com.example.nftmarket.service;

import com.example.nftmarket.entity.Person;

public interface InitPersonContent {
    //添加两个龙蛋到我们用户的随机仓库之中
    String addTheDinosaurEgg(Person person);

    //孵化用户当前拥有的恐龙蛋操作
    String toHatchTheDinosaurEgg(Person person,Integer index);
}
