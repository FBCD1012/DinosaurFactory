package com.example.nftmarket.structs;

import ch.qos.logback.core.testUtil.NPEAppender;

public class HashTree {
    //根节点特性：1.父母哈希为零 2.只有子哈希 3.节点的类型必定是蛋类型
    Node root1;
    Node root2;

    Node normalNode;


    //设置两个根节点然后进行相关的节点操作
    public HashTree(Node root1, Node root2) {
        this.root1 = root1;
        this.root2 = root2;
    }
}
