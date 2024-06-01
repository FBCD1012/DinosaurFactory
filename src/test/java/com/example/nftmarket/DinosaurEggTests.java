package com.example.nftmarket;


import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.utils.RandomDinosaurEgg;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DinosaurEggTests {
    @Test
    void TestTheDinosaurEggTests(){
        System.out.println(RandomDinosaurEgg.randomEgg(new DinosaurEgg()).getEggId());
        System.out.println(RandomDinosaurEgg.randomEgg(new DinosaurEgg()).getEggId());
    }
}
