package com.example.nftmarket.entity;




//市场实体类进行相关操作

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("document")
public class Market {
    @Id
    //售卖恐龙的集合
    List<Dinosaur> dinosaurs = new ArrayList <>();
}
