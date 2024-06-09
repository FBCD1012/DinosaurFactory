package com.example.nftmarket.listener;


import com.example.nftmarket.entity.DinosaurEggImage;
import com.example.nftmarket.entity.DinosaurImage;
import com.example.nftmarket.repository.jpa.DinosaurImageRepository;
import com.example.nftmarket.repository.jpa.ImageRepository;
import com.example.nftmarket.utils.IpfsUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationInit implements ApplicationListener<ApplicationStartedEvent> {
    @Resource
    ImageRepository imageRepository;

    @Resource
    DinosaurImageRepository dinosaurImageRepository;
    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        long count = imageRepository.count();
        long count2= dinosaurImageRepository.count();
        for (int i = 1; i <= count; i++) {
            Optional<DinosaurEggImage> byId = imageRepository.findById(i);
            IpfsUtils.download(byId.get().getImage_key().trim());
        }
        for (int i = 1; i <= count2; i++) {
            Optional<DinosaurImage> byId = dinosaurImageRepository.findById(i);
            IpfsUtils.downloadDinosaur(byId.get().getDinosaur_key().trim());
        }
    }
}
