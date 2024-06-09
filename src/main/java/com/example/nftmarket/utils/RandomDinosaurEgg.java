package com.example.nftmarket.utils;

import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.repository.jpa.ImageRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Hash;

import java.util.Random;

//实现蛋的初始化操作
@Component
public class RandomDinosaurEgg {
    @Resource
    ImageRepository imageRepository;
    private static final String factorySource="0x00000000000000000000000000000000000000000000000000000000000000000";
    public DinosaurEgg randomEgg(DinosaurEgg dinosaurEg){
        Random random=new Random();
        System.out.println(imageRepository.count());
        int num=random.nextInt((int) imageRepository.count());
        dinosaurEg.setEggId(Hash.sha3String(String.valueOf(System.identityHashCode(dinosaurEg))));
        dinosaurEg.setDinosaurFather(factorySource);
        dinosaurEg.setDinosaurMother(factorySource);
        dinosaurEg.setEggPhotoURI(imageRepository.findById(num).get().getImage_key().trim());
        dinosaurEg.setHatched(true);
        return dinosaurEg;
    }
}
