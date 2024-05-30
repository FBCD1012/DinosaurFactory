package com.example.nftmarket.utils;


import com.example.nftmarket.entity.Dinosaur;
import org.web3j.crypto.Hash;

/**
 * @Parasm:实现单个恐龙的HASH值操作
 * */
public class HashUtils {
    public static String getHashIndex(Object object){
        return Hash.sha3String(String.valueOf(object));
    }
}
