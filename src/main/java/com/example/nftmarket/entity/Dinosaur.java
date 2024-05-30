package com.example.nftmarket.entity;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @Params:恐龙ID，恐龙性别，恐龙颜色，恐龙服装，恐龙拥有者，恐龙稀有度，恐龙图片URI，恐龙价格
 * @Params:母恐龙是否可以进行生育
 * */
@Data
@Accessors(chain = true)
@Builder
public class Dinosaur {
    //哈希值
    String DinosaurId;
    String DinosaurSex;
    String DinosaurType;
    Boolean isBreeding;
    //Details
    String DinosaurColor;
    String DinosaurClothing;
    //哈希值
    String DinosaurOwner;
    String DinosaurRarity;
    String DinosaurPhotoUri;
    Double DinosaurPrice;
}
