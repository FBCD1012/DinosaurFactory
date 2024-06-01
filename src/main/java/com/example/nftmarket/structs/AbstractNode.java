package com.example.nftmarket.structs;

public class AbstractNode {
//    //直接抽象为哈希值即可进行操作
//   String Hash;
//   //此处是伏笔
//   String Male;
   //此处直接就是相关的内容属性
   SimpleNodeChain simpleNodeChain;
   AbstractNode leftNode;
   AbstractNode rightNode;

    public AbstractNode(SimpleNodeChain simpleNodeChain) {
        this.simpleNodeChain =simpleNodeChain;
        this.leftNode=null;
        this.rightNode=null;
    }
}
