package com.example.nftmarket.entity;


import jakarta.persistence.*;
import lombok.Data;


//恐龙蛋图片
@Data
@Entity
@Table(name = "dinosaur_image")
public class DinosaurImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String dinosaur_key;
}
