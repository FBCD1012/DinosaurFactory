package com.example.nftmarket.utils;

import com.example.nftmarket.entity.Dinosaur;
import com.fasterxml.jackson.databind.BeanProperty;

import java.awt.*;
import java.util.Arrays;

public class RarityUtils {

    private static final DinosaurDetails.DinosaurType[] TypeValues = DinosaurDetails.DinosaurType.values();
    private static final DinosaurDetails.Clothing[] DinosaurClothing=DinosaurDetails.Clothing.values();
    private static final DinosaurDetails.Color[] colors=DinosaurDetails.Color.values();

    public static String getTheRarity(DinosaurDetails.DinosaurType dinosaurType,
                                      DinosaurDetails.Clothing dinosaurClothing,
                                      DinosaurDetails.Color dinosaurColor){
        int typeWeights = Arrays.stream(TypeValues).toList().indexOf(dinosaurType);
        int clothingWeights=Arrays.stream(DinosaurClothing).toList().indexOf(dinosaurClothing);
        int colorWeights=Arrays.stream(colors).toList().indexOf(dinosaurColor);
        //权重相乘最大是100 我们设置0~6 6~12 12~18 18~24 24~30 30~36 实现稀有度的设置 白色 蓝色 紫色 金色 红色

        int RaritySums=typeWeights*clothingWeights*colorWeights;
        int RarityCase=
                ((RaritySums>=0)?1:0)
                +((RaritySums>=6)?1:0)
                +((RaritySums>=12)?1:0)
                +((RaritySums>=18)?1:0)
                +((RaritySums>=24)?1:0)
                +((RaritySums>=30)?1:0);
        return switch (RarityCase) {
            case 1 -> "White";
            case 2 -> "Blue";
            case 3 -> "Purple";
            case 4 -> "Gold";
            case 5 -> "Red";
            case 6 ->"Legendary";
            default -> "稀有度";
        };
    }
}
