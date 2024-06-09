package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.DinosaurImage;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.repository.jpa.DinosaurImageRepository;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Hash;

import java.util.Random;


//构建孵化实现类
@Service
public class HatchedImpl implements Hatched {
    @Resource
    DinosaurImageRepository dinosaurImageRepository;
    @Override
    public boolean isHatched(DinosaurEgg dinosaurEgg) {
        return dinosaurEgg.isHatched();
    }

    @Override
    public Dinosaur toHatch(Person person, DinosaurEgg dinosaurEgg,DinosaurRandomUtils dinosaurRandomUtils) {
        boolean hatched = isHatched(dinosaurEgg);
        Random random=new Random();
        int dinosaurNum= (int) dinosaurImageRepository.count();
        int randomNum = random.nextInt(dinosaurNum);
        if (randomNum==0) {
            randomNum++;
        }
        if (hatched){
            //定义一个新规则：蛋的地址进行一次哈希就是孵化出来的恐龙的哈希
            Dinosaur dinosaur=Dinosaur
                    .builder()
                    .build()
                    .setDinosaurId(Hash.sha3String(dinosaurEgg.toString()))
                    .setDinosaurColor(dinosaurRandomUtils.getTheRandomColor())
                    .setDinosaurClothing(dinosaurRandomUtils.getTheRandomClothing())
                    .setDinosaurType(dinosaurRandomUtils.getTheRandomType())
                    .setDinosaurRarity(dinosaurRandomUtils.getTheRarity())
                    .setDinosaurPrice(dinosaurRandomUtils.getThePrice())
                    .setIsBreeding(true)
                    .setDinosaurPhotoUri(dinosaurImageRepository.findById(randomNum).get().getDinosaur_key())
                    .setDinosaurSex(dinosaurRandomUtils.getTheRandomDinosaurSex())
                    .setSourceHash(dinosaurEgg.getEggId())
                    .setSaleSate("空闲")
                    .setDinosaurOwner(Hash.sha3String(person.toString()));
            dinosaurEgg.setChildHash(dinosaur.getDinosaurId());
            return dinosaur;
        }
        return null;
    }
}
