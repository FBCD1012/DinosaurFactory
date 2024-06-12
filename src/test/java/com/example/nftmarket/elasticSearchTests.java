package com.example.nftmarket;


import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.repository.elasticsearch.DinosaurMarketRepository;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Hash;

import java.util.Optional;

@SpringBootTest
public class elasticSearchTests {


    //查询相关的操作实现此处的ES模糊查询操作

    //构建此处的恐龙市场搜索仓库
    @Resource
    DinosaurMarketRepository dinosaurMarketResposiory;
    @Test
    void getTheDinosaurInfo(){
//       DinosaurRandomUtils dinosaurRandomUtils=new DinosaurRandomUtils();
//        Dinosaur dinosaur= Dinosaur
//                .builder()
//                .build()
//                .setDinosaurId(Hash.sha3String("fbcd"))
//                .setDinosaurColor(dinosaurRandomUtils.getTheRandomColor())
//                .setDinosaurClothing(dinosaurRandomUtils.getTheRandomClothing())
//                .setDinosaurType(dinosaurRandomUtils.getTheRandomType())
//                .setDinosaurRarity(dinosaurRandomUtils.getTheRarity())
//                .setDinosaurPrice(dinosaurRandomUtils.getThePrice())
//                .setIsBreeding(true)
//                .setDinosaurPhotoUri("QmbjUCWSmTszqoegRgSs4aHBmGtTrqgNQyUSXzGXwsp5Q3")
//                .setDinosaurSex(dinosaurRandomUtils.getTheRandomDinosaurSex())
//                .setDinosaurOwner(null);
////        System.out.println(dinosaurMarketResposiory.save(dinosaur));
//        Optional<Dinosaur> fbcd = dinosaurMarketResposiory.findById(Hash.sha3String("fbcd"));
//        Dinosaur dinosaur1 = fbcd.get();
//        System.out.println(dinosaur1.getDinosaurPhotoUri());
        dinosaurMarketResposiory.deleteAll();
    }


}
