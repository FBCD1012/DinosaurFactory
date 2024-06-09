package com.example.nftmarket;

import com.example.nftmarket.entity.Image;
import com.example.nftmarket.repository.jpa.ImageRepository;
import com.example.nftmarket.utils.IpfsUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest
public class JpaTests {
    @Resource
    ImageRepository imageRepository;
    @Test
    void getTheImageInfo() throws IOException {
        Optional<Image> byId = imageRepository.findById(2);
        System.out.println(byId.get().getImage_key().trim().equals("QmWQFz8FgRomSwLjbKqVZwXjYqYagrNNfo9xtSWATcqhjf"));
        System.out.println(IpfsUtils.download(byId.get().getImage_key().trim()));
    }
}
