package com.example.nftmarket.repository;

import com.example.nftmarket.entity.Dinosaur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DinosaurRepository extends ElasticsearchRepository<Dinosaur, String> {
    // 自定义查询方法：根据类型查询恐龙
    List<Dinosaur> findByDinosaurType(String dinosaurType);
    //颜色查询
    List<Dinosaur> findByDinosaurColor(String dinosaurColor);
    //性别查询
    List<Dinosaur> findByDinosaurSex(String dinosaurSex);
}
