package com.example.nftmarket.structs;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;

public class AbstractNode {
    //直接抽象为哈希值即可进行操作
   String Hash;
   //此处是伏笔
   String Male;
   //此处直接就是相关的内容属性
   SimpleNodeChain simpleNodeChain;
   Node<AbstractNode> leftNode;
   Node<AbstractNode> rightNode;

    public AbstractNode(String hash) {
        Hash = hash;
        this.leftNode=null;
        this.rightNode=null;
    }
}
