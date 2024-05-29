package com.example.nftmarket.entity;


import lombok.Data;

import java.util.List;

@Data
public class Person {
    //个人的账户哈希值
    String PersonHash;
    //个人拥有的恐龙数量
    Integer DinosaurCounts;
    //个人拥有的代币数量
    Integer DinosaurCoinCounts;
    //孵化仓(利用栈的数据结构来进行操作，两个恐龙入栈，孵化出恐龙蛋之后出栈)
    String HatchRepository;
    //售卖仓(直接使用集合来进行恐龙售卖信息的操作)
    List<Dinosaur> DinosaurSaleRepository;
}
