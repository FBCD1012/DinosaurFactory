package com.example.nftmarket.structs;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import lombok.Data;

@Data
public class SimpleNodeChain {
    Node<DinosaurEgg> root;

    //直接实现相关的node节点的初始化操作
    public SimpleNodeChain(Node<DinosaurEgg> root) {
        this.root = root;
    }
    public void setNodeChain(Node<Dinosaur> FatherNode, Node<Dinosaur> MotherNode){
        root.fatherNode=FatherNode;
        root.motherNode=MotherNode;
    }
}