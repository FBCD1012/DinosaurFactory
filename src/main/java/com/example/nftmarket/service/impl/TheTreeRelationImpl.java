package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.service.TheTreeRelation;
import com.example.nftmarket.structs.Node;
import com.example.nftmarket.structs.SimpleNodeChain;
import org.springframework.stereotype.Service;

@Service
public class TheTreeRelationImpl implements TheTreeRelation {
    Node<Dinosaur> dinosaurNodeFather;
    Node<Dinosaur> dinosaurNodeMother;
    //设置内部抽象数据结果的目的其实已经达到了，Index的数字可以到前端中进行操作实现获取操作
    @Override
    public SimpleNodeChain setTheSimpleNodeChain(DinosaurEgg dinosaurEgg,
                                                 Dinosaur dinosaurFather,
                                                 Dinosaur dinosaurMother,
                                                 Node<DinosaurEgg> sourceNode) {
        Node<DinosaurEgg> dinosaurEgg2=new Node<>(dinosaurEgg,null);
        //将参数放入既定的数据结构中，并且实现非空地判定操作
        if (sourceNode==null){
            dinosaurNodeFather=new Node<>(dinosaurFather,null);
            dinosaurNodeMother=new Node<>(dinosaurMother,null);
        }else {
            if (sourceNode.getNodeInfo().getEggId().equals(dinosaurFather.getSourceHash())){
                dinosaurNodeFather=new Node<>(dinosaurFather,sourceNode);
            }else {
                dinosaurNodeFather=new Node<>(dinosaurFather,null);
            }
            if (sourceNode.getNodeInfo().getEggId().equals(dinosaurMother.getSourceHash())){
                dinosaurNodeMother=new Node<>(dinosaurMother, sourceNode);
            }else {
                dinosaurNodeFather=new Node<>(dinosaurMother,null);
            }
        }
        SimpleNodeChain simpleNode=new SimpleNodeChain(dinosaurEgg2);
        simpleNode.setNodeChain(dinosaurNodeFather, dinosaurNodeMother);
        return simpleNode;
    }
}
