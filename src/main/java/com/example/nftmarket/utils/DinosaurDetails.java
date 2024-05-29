package com.example.nftmarket.utils;


import java.util.Random;

/**
 * @Params:实现恐龙装饰细节的随机操作
 */
public class DinosaurDetails {
    private static Random random=new Random();

    enum DinosaurSex{
        MALE,FEMALE
    }

    enum DinosaurType{
        Triceratops,TyrannosaurusRex,Raptor,ChineseDragon
    }
    enum Color{
        RED,YELLOW,BLUE,BLACK,GREEN
    }
    enum Clothing{
        JACKET,SHIRTS,ASTRONAUTS,SUIT
    }

    public static String  getTheRandomType(){
        DinosaurSex[] dinosaurSex=DinosaurSex.values();
        return dinosaurSex[random.nextInt(dinosaurSex.length)].toString();
    }

    public static String  getTheRandomSex(){
        DinosaurType[] dinosaurType=DinosaurType.values();
        return dinosaurType[random.nextInt(dinosaurType.length)].toString();
    }

    public static String  getTheRandomColor(){
        Color[] colors=Color.values();
        return colors[random.nextInt(colors.length)].toString();
    }
    public static String getTheRandomClothing() {
        Clothing[] clothing=Clothing.values();
        return clothing[random.nextInt(clothing.length)].toString();
    }
}
