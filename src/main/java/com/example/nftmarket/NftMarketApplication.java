package com.example.nftmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class NftMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(NftMarketApplication.class, args);
    }

}
