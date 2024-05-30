package com.example.nftmarket.utils;



//根据恐龙的稀有度以及恐龙的颜色来进行搭配操作
public class PriceUtils {
    private static final RarityUtils.Rarities[] Rarites = RarityUtils.Rarities.values();
    private static final DinosaurDetails.Color[] colors=DinosaurDetails.Color.values();
    public static Double getThePrice(RarityUtils.Rarities rarities, DinosaurDetails.Color color){
        return 0.01854;
    }
}
