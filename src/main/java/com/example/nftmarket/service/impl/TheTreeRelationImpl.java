package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.service.TheTreeRelation;
import com.example.nftmarket.structs.Node;
import com.example.nftmarket.structs.SimpleNodeChain;

public class TheTreeRelationImpl implements TheTreeRelation {
    Node<Dinosaur> dinosaurNodeFather;
    Node<Dinosaur> dinosaurNodeMother;
    @Override
    public SimpleNodeChain setTheSimpleNodeChain(DinosaurEgg dinosaurEgg,
                                                 Dinosaur dinosaurFather,
                                                 Dinosaur dinosaurMother,
                                                 Node<DinosaurEgg> sourceNode) {
        Node<DinosaurEgg> dinosaurEgg2=new Node<>(dinosaurEgg,null);
        if (sourceNode.getNodeInfo().getEggId().equals(dinosaurFather.getSourceHash())){
            dinosaurNodeFather=new Node<>(dinosaurFather,sourceNode);
        }else {
            dinosaurNodeFather=new Node<>(dinosaurFather,null);
        }
        if (dinosaurMother.getSourceHash().equals(sourceNode.getNodeInfo().getEggId())){
            dinosaurNodeMother=new Node<>(dinosaurFather, sourceNode);
        }else {
            dinosaurNodeFather=new Node<>(dinosaurMother,null);
        }

        SimpleNodeChain simpleNode=new SimpleNodeChain(dinosaurEgg2);
        simpleNode.setNodeChain(dinosaurNodeFather, dinosaurNodeMother);
        return simpleNode;
    }
}
