package com.example.nftmarket.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    //个人的账户哈希值
    String PersonHash;
    //个人拥有的恐龙数量
    Integer DinosaurCounts;
    //个人拥有的代币数量
    Integer DinosaurCoinCounts;
    //雄性恐龙储存集合
    List<Dinosaur> MaleDinosaurRepository=new ArrayList<>();
    //雌性恐龙储存集合
    List<Dinosaur> FeMaleDinosaurRepository=new ArrayList<>();
    //龙蛋储存集合
    List<DinosaurEgg> dinosaurEggsRepository=new ArrayList<>();
    //售卖集合(直接使用集合来进行恐龙售卖信息的操作)
    List<Dinosaur> DinosaurSaleRepository=new ArrayList<>();
}
