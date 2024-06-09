package com.example.nftmarket.entity;


import jakarta.persistence.*;
import lombok.Data;


//恐龙蛋图片
@Data
@Entity
@Table(name = "image_info")
public class DinosaurEggImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String image_key;
}
