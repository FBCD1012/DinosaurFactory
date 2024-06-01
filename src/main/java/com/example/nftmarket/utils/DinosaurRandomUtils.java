package com.example.nftmarket.utils;

import com.example.nftmarket.service.DinosaurRarity;
import com.example.nftmarket.service.impl.DinosaurRarityImpl;


//优化了整体的类的相关信息的嵌入，测试并不需要更多的冗余调用了，究极完美的封装操作
public class DinosaurRandomUtils {
    static DinosaurDetails.Color theRandomColor;
    static DinosaurDetails.DinosaurType theRandomType;
    static DinosaurDetails.Clothing theRandomClothing;
    static DinosaurDetails.DinosaurSex theRandomDinosaurSex;
    static DinosaurRarity dinosaurRarity;

    public DinosaurRandomUtils() {
        theRandomColor = DinosaurDetails.getTheRandomColor();
        theRandomType = DinosaurDetails.getTheRandomType();
        theRandomClothing= DinosaurDetails.getTheRandomClothing();
        theRandomDinosaurSex=DinosaurDetails.getTheRandomSex();
        dinosaurRarity = new DinosaurRarityImpl();
    }

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
