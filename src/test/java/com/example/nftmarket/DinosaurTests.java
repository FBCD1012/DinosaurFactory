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
        DinosaurRandomUtils dinosaurRandomUtils=new DinosaurRandomUtils();
        Dinosaur dinosaur=new Dinosaur();
        dinosaur.setDinosaurId(Hash.sha3String(String.valueOf(dinosaur)));
        dinosaur.setDinosaurColor(dinosaurRandomUtils.getTheRandomColor());
        dinosaur.setDinosaurType(dinosaurRandomUtils.getTheRandomType());
        dinosaur.setDinosaurClothing(dinosaurRandomUtils.getTheRandomClothing());
        dinosaur.setDinosaurRarity(dinosaurRandomUtils.getTheRarity());
        dinosaur.setDinosaurSex(dinosaurRandomUtils.getTheRandomDinosaurSex());
        dinosaur.setIsBreeding(true);
        dinosaur.setDinosaurSex("MALE");
        dinosaur.setDinosaurPrice(dinosaurRandomUtils.getThePrice());
        dinosaur.setDinosaurPhotoUri(null);
        System.out.println(dinosaur);
    }
}
