package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurAgg;
import com.example.nftmarket.service.Hatched;
import org.springframework.stereotype.Service;


//构建孵化实现类
@Service
public class HatchedImpl implements Hatched {
    @Override
    public boolean isHatched(DinosaurAgg dinosaurAgg) {
        return dinosaurAgg.isHatched();
    }

    @Override
    public Dinosaur toHatch(DinosaurAgg dinosaurAgg) {
        boolean hatched = isHatched(dinosaurAgg);
        if (hatched){
            Dinosaur dinosaur=new Dinosaur();
        }
        return null;
    }
}
