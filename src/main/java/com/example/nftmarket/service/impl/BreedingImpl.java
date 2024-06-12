package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.repository.jpa.DinosaurImageRepository;
import com.example.nftmarket.repository.jpa.ImageRepository;
import com.example.nftmarket.service.Breeding;
import com.example.nftmarket.utils.HashUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Random;

//实现恐龙交配方法
@Service
public class BreedingImpl implements Breeding {

    //构建此处的图床仓库进行其中的图片选取操作
    @Resource
    ImageRepository imageRepository;

    //此处注意结合相关数据结构进行操作
    @Override
    public DinosaurEgg creatDinosaurEgg(Person person, Dinosaur dinosaurMother, Dinosaur dinosaurFather) {
//        if (dinosaurMother.getSaleSate().equals("售卖中") || dinosaurFather.getSaleSate().equals("售卖中")){
//            return null;
//        }
        Random random =new Random();
        int dinosaurNum=(int) imageRepository.count();
        int i = random.nextInt(dinosaurNum);
        if (i==0){
            i++;
        }
        DinosaurEgg dinosaurEgg=new DinosaurEgg();
        dinosaurEgg.setDinosaurMother(dinosaurMother.getDinosaurId());
        dinosaurEgg.setDinosaurFather(dinosaurFather.getDinosaurId());
        dinosaurEgg.setEggId(HashUtils.getHashIndex(dinosaurEgg));
        dinosaurEgg.setHatched(true);
        //构建恐龙图片操作
        dinosaurEgg.setEggPhotoURI(imageRepository.findById(i).get().getImage_key());
        person.getDinosaurEggsRepository().add(dinosaurEgg);
        //一旦孵化出一个蛋之后，恐龙母亲就不能进行相关操作了
        dinosaurMother.setIsBreeding(false);
        dinosaurFather.setIsBreeding(false);
        return dinosaurEgg;
    }
    //内部检验方法进行恐龙是否能够进行孵化操作
    public Dinosaur getTheDinosaurMother(Person person, Integer index) {
        Dinosaur dinosaur = person.getFeMaleDinosaurRepository().get(index);
        if (dinosaur==null){
            System.out.println("your dinosaur is null");
            return null;
        }else if (!isBreeding(dinosaur)){
            System.out.println("your mother-dinosaur is not breeding");
            return null;
        }
        return dinosaur;
    }

    public Dinosaur getTheDinosaurFather(Person person, Integer index) {
        Dinosaur dinosaur = person.getMaleDinosaurRepository().get(index);
        if (dinosaur==null){
            System.out.println("your dinosaur is null");
            return null;
        }else if (!isBreeding(dinosaur)){
            System.out.println("your father-dinosaur is not breeding");
            return null;
        }
        return dinosaur;
    }
    @Override
    public boolean isBreeding(Dinosaur dinosaurMotherOrFather) {
        return dinosaurMotherOrFather.getIsBreeding();
    }
}
