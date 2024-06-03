package com.example.nftmarket;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaseTests {
    @Test
    void theCase(){
        int num=30;
        int RarityCase=((num<20)?0:1);
        System.out.println(RarityCase);
    }

}
