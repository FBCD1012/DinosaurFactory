package com.example.nftmarket.entity;


import lombok.Data;
/**
 * @Params:龙蛋ID，龙父哈希值，龙母哈希值，是否孵化中
 **/
@Data
public class DinosaurAgg {
    Integer AggId;
    String DinosaurFather;
    String DinosaurMother;
    boolean isHatched;
}
