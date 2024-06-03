package com.example.nftmarket;


import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Hash;

@SpringBootTest
public class DinosaurTests {
    @Test
    void DinosaurTest() {

        while (true){
        DinosaurRandomUtils dinosaurRandomUtils=new DinosaurRandomUtils();
        Dinosaur dinosaur= Dinosaur
                .builder()
                .build()
                .setDinosaurId(Hash.sha3String("fbcd"))
                .setDinosaurColor(dinosaurRandomUtils.getTheRandomColor())
                .setDinosaurClothing(dinosaurRandomUtils.getTheRandomClothing())
                .setDinosaurType(dinosaurRandomUtils.getTheRandomType())
                .setDinosaurRarity(dinosaurRandomUtils.getTheRarity())
                .setDinosaurPrice(dinosaurRandomUtils.getThePrice())
                .setIsBreeding(true)
                .setDinosaurPhotoUri(null)
                .setDinosaurSex(dinosaurRandomUtils.getTheRandomDinosaurSex())
                .setDinosaurOwner(null);
        System.out.println(dinosaur);
        }
    }

}
