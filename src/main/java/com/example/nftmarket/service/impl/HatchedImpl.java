package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.utils.DinosaurRandomUtils;
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
    public Dinosaur toHatch(Person person, DinosaurEgg dinosaurEgg,DinosaurRandomUtils dinosaurRandomUtils) {
        boolean hatched = isHatched(dinosaurEgg);
        if (hatched){
            //定义一个新规则：蛋的地址进行一次哈希就是孵化出来的恐龙的哈希
            return Dinosaur
                    .builder()
                    .build()
                    .setDinosaurId(Hash.sha3String(dinosaurEgg.toString()))
                    .setDinosaurColor(dinosaurRandomUtils.getTheRandomColor())
                    .setDinosaurClothing(dinosaurRandomUtils.getTheRandomClothing())
                    .setDinosaurType(dinosaurRandomUtils.getTheRandomType())
                    .setDinosaurRarity(dinosaurRandomUtils.getTheRarity())
                    .setDinosaurPrice(dinosaurRandomUtils.getThePrice())
                    .setIsBreeding(true)
                    .setDinosaurPhotoUri(null)
                    .setDinosaurSex(dinosaurRandomUtils.getTheRandomDinosaurSex())
                    .setDinosaurOwner(Hash.sha3String(person.toString()));
        }
        return null;
    }
}
