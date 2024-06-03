package com.example.nftmarket.utils;


import org.web3j.crypto.Hash;

/**
 * @Parasm:实现单个恐龙的HASH值操作
 * */
public class HashUtils {
    public static String getHashIndex(Object object){
        //注意此处的获取一个对象的哈希值操作 对象的hashCode表示的是内容的哈希code计算操作，对应不了对象的地址，
        // 此处获取的方法是获取对象的实际内存地址，这样就能实现每一个对象的哈希值都是不同的字符串
        return Hash.sha3String(String.valueOf(System.identityHashCode(object)));
    }
}
