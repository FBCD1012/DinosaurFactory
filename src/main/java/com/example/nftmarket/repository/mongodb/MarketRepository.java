package com.example.nftmarket.repository.mongodb;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.Market;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarketRepository extends MongoRepository<Market, List<Dinosaur>> {
}
