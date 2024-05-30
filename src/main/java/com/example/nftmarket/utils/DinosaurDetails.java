package com.example.nftmarket.utils;


import java.util.Random;

/**
 * @Params:实现恐龙装饰细节的随机操作
 */
public class DinosaurDetails {
    private static final Random random=new Random();

    public enum DinosaurSex{
        MALE,FEMALE
    }
    public enum DinosaurType{
        Triceratops,TyrannosaurusRex,Raptor,ChineseDragon
    }
    public enum Color{
        RED,YELLOW,BLUE,BLACK,GREEN
    }
    public enum Clothing{
        JACKET,SHIRTS,ASTRONAUTS,SUIT
    }

    public static DinosaurSex  getTheRandomSex(){
        DinosaurSex[] dinosaurSex=DinosaurSex.values();
        return dinosaurSex[random.nextInt(dinosaurSex.length)];
    }

    public static DinosaurType getTheRandomType(){
        DinosaurType[] dinosaurType=DinosaurType.values();
        return dinosaurType[random.nextInt(dinosaurType.length)];
    }

    public static Color getTheRandomColor(){
        Color[] colors=Color.values();
        return colors[random.nextInt(colors.length)];
    }
    public static Clothing getTheRandomClothing() {
        Clothing[] clothing=Clothing.values();
        return clothing[random.nextInt(clothing.length)];
    }
}
