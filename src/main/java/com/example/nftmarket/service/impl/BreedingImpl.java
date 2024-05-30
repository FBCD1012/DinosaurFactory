package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.service.Breeding;
import org.springframework.stereotype.Service;

//实现恐龙交配方法
@Service
public class BreedingImpl implements Breeding {
    @Override
    public DinosaurEgg creatDinosaurEgg(Dinosaur dinosaurFatherOrMother, Dinosaur dinosaurMotherOrFather) {
        return null;
    }

    @Override
    public boolean isBreeding(Dinosaur dinosaurMother) {
        return false;
    }
}
