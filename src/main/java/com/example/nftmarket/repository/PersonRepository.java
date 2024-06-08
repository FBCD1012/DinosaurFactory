package com.example.nftmarket.repository;

import com.example.nftmarket.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person,String> {
}
