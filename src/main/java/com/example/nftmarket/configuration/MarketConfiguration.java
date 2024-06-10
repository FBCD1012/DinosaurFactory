package com.example.nftmarket.configuration;


import com.example.nftmarket.entity.Market;
import com.example.nftmarket.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketConfiguration {
    //初始化用户类进行操作
    @Bean
    public Person personInit(){
        return new Person();
    }

    //初始化市场类，用于将类的相关信息包含到整体项目中进行求解操作实现
    @Bean
    public Market MarketInit(){
        return new Market();
    }
}
