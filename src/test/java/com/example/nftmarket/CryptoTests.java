package com.example.nftmarket;


import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Hash;

@SpringBootTest
public class CryptoTests {
    @Test
    public void hashTest(){
        System.out.println(Hash.sha3String("fbcd"));
    }
}
