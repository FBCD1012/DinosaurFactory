package com.example.nftmarket.utils;

import com.example.nftmarket.entity.DinosaurEgg;
import org.web3j.crypto.Hash;

//实现蛋的初始化操作
public class RandomDinosaurEgg {
    private static final String factorySource="0x00000000000000000000000000000000000000000000000000000000000000000";
    public static DinosaurEgg randomEgg(DinosaurEgg dinosaurEg){
        dinosaurEg.setEggId(Hash.sha3String(dinosaurEg.toString()));
        dinosaurEg.setDinosaurFather(factorySource);
        dinosaurEg.setDinosaurMother(factorySource);
        dinosaurEg.setHatched(true);
        return dinosaurEg;
    }
}
