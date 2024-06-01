package com.example.nftmarket.structs;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import lombok.Data;

@Data
//构建泛型类节点实现一个哈希树操作
public class Node<D> {
    Node<Dinosaur> fatherNode;
    Node<Dinosaur> motherNode;
    Node<DinosaurEgg> childNode;
    Node<DinosaurEgg> sourceNode;
    D nodeInfo;

    public Node(D nodeInfo,Node<DinosaurEgg> sourceNode) {
        this.fatherNode=null;
        this.motherNode=null;
        this.childNode=null;
        this.sourceNode =sourceNode;
        this.nodeInfo=nodeInfo;
    }
}
