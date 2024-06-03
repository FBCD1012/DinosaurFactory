package com.example.nftmarket;

import com.example.nftmarket.utils.DinosaurDetails;
import com.example.nftmarket.utils.PriceUtils;
import com.example.nftmarket.utils.RarityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceTests {
    @Test
    void priceTest() {
        System.out.println(PriceUtils.getThePrice(RarityUtils.getTheRarity(DinosaurDetails.getTheRandomType(),
                DinosaurDetails.getTheRandomClothing(),
                DinosaurDetails.getTheRandomColor()),DinosaurDetails.getTheRandomColor()));
    }
}
