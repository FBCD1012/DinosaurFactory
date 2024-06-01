package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.service.InitPersonContent;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import com.example.nftmarket.utils.RandomDinosaurEgg;

import java.util.ArrayList;
import java.util.List;

//实现了新加入用户的两个免费蛋的增加操作
public class InitPersonContentImpl implements InitPersonContent {
    List<DinosaurEgg> dinosaurEggs=new ArrayList<>();
    @Override
    public String addTheDinosaurEgg(Person person) {
        DinosaurEgg dinosaurEgg = RandomDinosaurEgg.randomEgg(new DinosaurEgg());
        DinosaurEgg dinosaurEgg1 = RandomDinosaurEgg.randomEgg(new DinosaurEgg());
        dinosaurEggs.add(dinosaurEgg); dinosaurEggs.add(dinosaurEgg1);
        person.setDinosaurEggsRepository(dinosaurEggs);
        return "You have obtained two official dinosaur eggs";
    }

    @Override
    public String toHatchTheDinosaurEgg(Person person,Integer eggIndex) {
        Hatched hatched=new HatchedImpl();
        hatched.toHatch(person, person.getDinosaurEggsRepository().get(eggIndex), new DinosaurRandomUtils());
        return "You've hatched the dinosaurs";
    }

}
