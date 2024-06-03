package com.example.nftmarket.entity;




//市场实体类进行相关操作

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Market {
    //售卖恐龙的集合
    List<Dinosaur> dinosaurs = new ArrayList<>();
}
