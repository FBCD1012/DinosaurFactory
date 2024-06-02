package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Breeding;
import org.springframework.stereotype.Service;

//实现恐龙交配方法
@Service
public class BreedingImpl implements Breeding {
    @Override
    public DinosaurEgg creatDinosaurEgg(Person person, Dinosaur dinosaurFatherOrMother, Dinosaur dinosaurMotherOrFather) {

        return null;
    }
    public Dinosaur getTheDinosaurMother(Person person, Integer index) {
        Dinosaur dinosaur = person.getFeMaleDinosaurRepository().get(index);
        if (dinosaur==null){
            System.out.println("your dinosaur is null");
            return null;
        }else if (!dinosaur.getIsBreeding()){
            System.out.println("your mother-dinosaur is not breeding");
            return null;
        }
        return dinosaur;
    }

    public Dinosaur getTheDinosaurFather(Person person, Integer index) {
        return person.getMaleDinosaurRepository().get(index);
    }
    @Override
    public boolean isBreeding(Dinosaur dinosaurMother) {
        return false;
    }
}
