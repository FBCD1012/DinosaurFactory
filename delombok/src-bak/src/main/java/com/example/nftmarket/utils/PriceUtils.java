package com.example.nftmarket.utils;


import java.util.Arrays;

//根据恐龙的稀有度以及恐龙的颜色来进行搭配操作
//平台收取的手续费+(NFT稀有度*颜色权重/统一时段售卖的数量)=最终的NFT基准定价
public class PriceUtils {
    private static final RarityUtils.Rarities[] Rarities = RarityUtils.Rarities.values();
    private static final DinosaurDetails.Color[] colors = DinosaurDetails.Color.values();

    public static Double getThePrice(RarityUtils.Rarities rarities, DinosaurDetails.Color color) {
        // 权重计算
        double colorWeights = Arrays.stream(colors).toList().indexOf(color);
        double Rarity = Arrays.stream(Rarities).toList().indexOf(rarities);
        double denominator = 10;
        //基础价格
        double basePrice =((Rarity+1) * (colorWeights+1)) / denominator;
        // 计算10%的手续费
        double fee = basePrice * 0.1;
        //返回最终价格
        return basePrice + fee;
    }
}