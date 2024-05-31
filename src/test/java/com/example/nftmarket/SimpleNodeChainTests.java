package com.example.nftmarket;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.structs.Node;
import com.example.nftmarket.structs.SimpleNodeChain;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleNodeChainTests {
    @Test
    void SimpleNodeChainMethod(){
        DinosaurEgg dinosaurEgg=new DinosaurEgg();
        Dinosaur dinosaur= Dinosaur.builder().build();
        Dinosaur dinosaur4=Dinosaur.builder().build();
        Node<DinosaurEgg> dinosaurEgg2=new Node<>(dinosaurEgg);
        Node<Dinosaur> dinosaur2=new Node<>(dinosaur);
        Node<Dinosaur> dinosaur3=new Node<>(dinosaur4);
        SimpleNodeChain dinosaurEggSimpleNodeChain=new SimpleNodeChain(dinosaurEgg2);
        dinosaurEggSimpleNodeChain.setNodeChain(dinosaur2,dinosaur3);
        System.out.println(dinosaurEggSimpleNodeChain);
        System.out.println(dinosaurEggSimpleNodeChain.getRoot());
    }
}
