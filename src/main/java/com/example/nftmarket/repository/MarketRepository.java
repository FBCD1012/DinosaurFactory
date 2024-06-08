package com.example.nftmarket.repository;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.Market;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MarketRepository extends MongoRepository<Market, List<Dinosaur>> {
}
