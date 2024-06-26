package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.service.PersonContent;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import com.example.nftmarket.utils.RandomDinosaurEgg;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//实现了新加入用户的两个免费蛋的增加操作
public class PersonContentImpl implements PersonContent {
    @Resource
    RandomDinosaurEgg randomDinosaurEgg;
    @Resource
    Hatched hatched;

    @Override
    public String addTheDinosaurEgg(Person person) {
        DinosaurEgg dinosaurEg=new DinosaurEgg();
        DinosaurEgg dinosaurEg2=new DinosaurEgg();
        //@Data的tostring会导致我们进行哈希值操作的时候出现一些相同内容的哈希，进而导致我们获取到的哈希值出现一定的异常情况
        // ，所以说我们此处直接获取对象的HashCode地址进行操作
        DinosaurEgg dinosaurEgg = randomDinosaurEgg.randomEgg(dinosaurEg);
        DinosaurEgg dinosaurEgg1 = randomDinosaurEgg.randomEgg(dinosaurEg2);
        person.getDinosaurEggsRepository().add(dinosaurEgg); person.getDinosaurEggsRepository().add(dinosaurEgg1);
        return "You have obtained two official dinosaur eggs";
    }

    @Override
    public Dinosaur toHatchTheDinosaurEgg(Person person, Integer eggIndex) {
        System.out.println("You have successfully hatched a dinosaur");
        Dinosaur dinosaur = hatched.toHatch(person, person.getDinosaurEggsRepository().get(eggIndex)
                ,new DinosaurRandomUtils());
        switch (dinosaur.getDinosaurSex()) {
            case "MALE" -> person.getMaleDinosaurRepository().add(dinosaur);
            case "FEMALE" -> person.getFeMaleDinosaurRepository().add(dinosaur);
        }
        List<DinosaurEgg> theDinosaurEgg = getTheDinosaurEgg(person);
        DinosaurEgg dinosaurEgg = theDinosaurEgg.get(eggIndex);
        dinosaurEgg.setHatched(false);
        return dinosaur;
    }

    //获取储存龙蛋的数据结构
    @Override
    public List<DinosaurEgg> getTheDinosaurEgg(Person person) {
        return person.getDinosaurEggsRepository();
    }
    //获取用户的所有恐龙数量，包括母恐龙以及父恐龙数量
    @Override
    public int getDinosaurCounts(Person person) {
        return person.getMaleDinosaurRepository().size()+
                person.getFeMaleDinosaurRepository().size();
    }

    @Override
    public List<Dinosaur> getDinosaurInfo(Person person) {
        List<Dinosaur> feMaleDinosaurRepository = person.getFeMaleDinosaurRepository();
        List<Dinosaur> meMaleDinosaurRepository=person.getMaleDinosaurRepository();
        List<Dinosaur> allDinosaurRepository=new ArrayList<>();
        allDinosaurRepository.addAll(feMaleDinosaurRepository);
        allDinosaurRepository.addAll(meMaleDinosaurRepository);
        return allDinosaurRepository;
    }

}
