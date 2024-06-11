package com.example.nftmarket.repository.elasticsearch;

import com.example.nftmarket.entity.Dinosaur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//构建恐龙ES仓库储藏市场售卖的恐龙

@EnableElasticsearchRepositories
public interface DinosaurMarketRepository extends ElasticsearchRepository<Dinosaur,String> {

}
