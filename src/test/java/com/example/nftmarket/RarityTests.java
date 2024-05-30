package com.example.nftmarket;


import com.example.nftmarket.utils.DinosaurDetails;
import com.example.nftmarket.utils.RarityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class RarityTests {
    @Test
    void getTheRarity(){
        int RaritySums=100;
        int RarityCase=((RaritySums>0)?1:0)
                +((RaritySums>20)?1:0)
                +((RaritySums>40)?1:0)
                +((RaritySums>60)?1:0)
                +((RaritySums>80)?1:0);

        System.out.println(RarityCase);
    }
    @Test
    void getTheRandom(){
        int i=0;
        while (i<1000) {
            System.out.println(RarityUtils.getTheRarity(DinosaurDetails.getTheRandomType(), DinosaurDetails.getTheRandomClothing(), DinosaurDetails.getTheRandomColor()));
            i++;
        }
    }
    @Test
    void  getTheWeights(){
    }
}
