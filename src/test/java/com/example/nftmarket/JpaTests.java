package com.example.nftmarket;

import com.example.nftmarket.repository.jpa.DinosaurImageRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class JpaTests {
    @Resource
    DinosaurImageRepository dinosaurImageRepository;
    @Test
    void getTheImageInfo() throws IOException {
        System.out.println(dinosaurImageRepository.findById(1));
//        Optional<DinosaurImage> byId = dinosaurRepository.findById(2);
//        System.out.println(byId.get().getDinosaur_image().trim().equals("QmWQFz8FgRomSwLjbKqVZwXjYqYagrNNfo9xtSWATcqhjf"));
//        System.out.println(IpfsUtils.download(byId.get().getDinosaur_image().trim()));
    }
}
