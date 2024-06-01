package com.example.nftmarket.entity;


import lombok.Data;
/**
 * @Params:龙蛋ID，龙父哈希值，龙母哈希值，是否孵化中
 **/
public class DinosaurEgg {
    String eggId;
    String DinosaurFather;
    String DinosaurMother;
    boolean isHatched;

    public String getEggId() {
        return eggId;
    }

    public void setEggId(String eggId) {
        this.eggId = eggId;
    }

    public String getDinosaurFather() {
        return DinosaurFather;
    }

    public void setDinosaurFather(String dinosaurFather) {
        DinosaurFather = dinosaurFather;
    }

    public String getDinosaurMother() {
        return DinosaurMother;
    }

    public void setDinosaurMother(String dinosaurMother) {
        DinosaurMother = dinosaurMother;
    }

    public boolean isHatched() {
        return isHatched;
    }

    public void setHatched(boolean hatched) {
        isHatched = hatched;
    }
}
