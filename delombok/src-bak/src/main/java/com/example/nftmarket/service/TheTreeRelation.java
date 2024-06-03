package com.example.nftmarket.service;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.structs.Node;
import com.example.nftmarket.structs.SimpleNodeChain;

public interface TheTreeRelation {
    //设置单个抽象节点操作
    SimpleNodeChain setTheSimpleNodeChain(DinosaurEgg dinosaurEgg,
                                          Dinosaur dinosaurMother,
                                          Dinosaur dinosaurFather,
                                          Node<DinosaurEgg> sourceNode);
}
