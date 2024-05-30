package com.example.nftmarket;


import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.utils.DinosaurDetails;
import com.example.nftmarket.utils.RarityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Hash;

@SpringBootTest
public class DinosaurTests {
    @Test
    void DinosaurTest() {
        DinosaurDetails.Color theRandomColor = DinosaurDetails.getTheRandomColor();
        DinosaurDetails.DinosaurType theRandomType = DinosaurDetails.getTheRandomType();
        DinosaurDetails.Clothing theRandomClothing = DinosaurDetails.getTheRandomClothing();

        Dinosaur dinosaur=new Dinosaur();
        dinosaur.setDinosaurId(Hash.sha3String(String.valueOf(dinosaur)));
        dinosaur.setDinosaurColor(String.valueOf(theRandomColor));
        dinosaur.setDinosaurType(String.valueOf(theRandomType));
        dinosaur.setDinosaurClothing(String.valueOf(theRandomClothing));
        dinosaur.setDinosaurRarity(RarityUtils.getTheRarity(theRandomType, theRandomClothing, theRandomColor).toString());
        dinosaur.setIsBreeding(true);
        dinosaur.setDinosaurSex(true);
        dinosaur.setDinosaurPrice(0.00);
        dinosaur.setDinosaurPhotoUri(null);
        System.out.println(dinosaur);
    }
}
