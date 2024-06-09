package com.example.nftmarket.repository.jpa;

import com.example.nftmarket.entity.DinosaurImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DinosaurImageRepository extends JpaRepository<DinosaurImage,Integer> {
}
