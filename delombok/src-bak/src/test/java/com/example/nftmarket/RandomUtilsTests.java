package com.example.nftmarket;


import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomUtilsTests {
    @Test
    void getTheRandom(){
        DinosaurRandomUtils randomUtil=new DinosaurRandomUtils();
        System.out.println(randomUtil.getTheRandomColor());
    }

}
