package com.example.nftmarket.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "image_info")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String image_key;
}
