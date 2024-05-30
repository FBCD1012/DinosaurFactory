package com.example.nftmarket.utils;

import com.example.nftmarket.service.DinosaurRarity;
import com.example.nftmarket.service.impl.DinosaurRarityImpl;


//优化了整体的类的相关信息的嵌入，测试并不需要更多的冗余调用了，究极完美的封装操作
public class DinosaurRandomUtils {
    private static final DinosaurDetails.Color theRandomColor = DinosaurDetails.getTheRandomColor();
    private static final DinosaurDetails.DinosaurType theRandomType = DinosaurDetails.getTheRandomType();
    private static final DinosaurDetails.Clothing theRandomClothing = DinosaurDetails.getTheRandomClothing();
    private static final DinosaurDetails.DinosaurSex theRandomDinosaurSex=DinosaurDetails.getTheRandomSex();
    private static final DinosaurRarity dinosaurRarity = new DinosaurRarityImpl();

    public String getTheRandomColor() {
        return String.valueOf(theRandomColor);
    }

    public String getTheRandomType() {
        return String.valueOf(theRandomType);
    }

    public String getTheRandomClothing() {
        return String.valueOf(theRandomClothing);
    }
    public String getTheRandomDinosaurSex(){
        return String.valueOf(theRandomDinosaurSex);
    }
    private static RarityUtils.Rarities getDinosaurRarityImpl() {
        return dinosaurRarity.giveDinosaurRarity(theRandomType, theRandomClothing, theRandomColor);
    }

    public String getTheRarity() {
        return getDinosaurRarityImpl().toString();
    }

    public Double getThePrice() {
        return dinosaurRarity.giveDinosaurPrice(getDinosaurRarityImpl(), theRandomColor);
    }
}
