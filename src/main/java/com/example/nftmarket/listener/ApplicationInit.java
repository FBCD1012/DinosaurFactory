package com.example.nftmarket.listener;


import com.example.nftmarket.entity.Image;
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
    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        long count = imageRepository.count();
        for (int i = 1; i <= count; i++) {
            Optional<Image> byId = imageRepository.findById(i);
            IpfsUtils.download(byId.get().getImage_key().trim());
        }

    }
}
