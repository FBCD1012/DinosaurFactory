package com.example.nftmarket.repository.mongodb;

import com.example.nftmarket.entity.Dinosaur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DinosaurRepository extends MongoRepository<Dinosaur, String> {
}
