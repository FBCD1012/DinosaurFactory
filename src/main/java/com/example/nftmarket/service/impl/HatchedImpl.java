package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.service.Hatched;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Hash;


//构建孵化实现类
@Service
public class HatchedImpl implements Hatched {
    @Override
    public boolean isHatched(DinosaurEgg dinosaurEgg) {
        return dinosaurEgg.isHatched();
    }

    @Override
    public Dinosaur toHatch(DinosaurEgg dinosaurEgg) {
        boolean hatched = isHatched(dinosaurEgg);
        if (hatched){
            //定义一个新规则：蛋的地址进行一次哈希就是孵化出来的恐龙的哈希
        }
        return null;
    }
}
