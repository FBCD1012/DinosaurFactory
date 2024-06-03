package com.example.nftmarket.utils;

import com.example.nftmarket.entity.Person;
import com.example.nftmarket.structs.NormalDinosaurTree;
import com.example.nftmarket.structs.SimpleNodeChain;

public class TreeUtils {
    static NormalDinosaurTree normalDinosaurTree;

    public TreeUtils(Person person) {
        normalDinosaurTree=new NormalDinosaurTree(person);
    }

    public  String setTheTreeNode(SimpleNodeChain node){
        normalDinosaurTree.addNode(node);
        return "Add node success!";
    }
    public  void traverserPreOrder(){
        normalDinosaurTree.traverserPreOrder(normalDinosaurTree.getRoot());
    }
}
