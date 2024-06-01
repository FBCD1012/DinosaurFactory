package com.example.nftmarket.structs;

import lombok.Data;

public class NormalDinosaurTree {
    AbstractNode root;

    private AbstractNode addNode(AbstractNode currentNode,SimpleNodeChain chain){
        if(currentNode==null){
            return new AbstractNode(chain);
        }
        if (currentNode.simpleNodeChain.root.fatherNode.sourceNode==chain.root){
            currentNode.leftNode=addNode(currentNode.leftNode,chain);
        }
        if (currentNode.simpleNodeChain.root.motherNode.sourceNode==chain.root){
            currentNode.rightNode=addNode(currentNode.rightNode, chain);
        }
        return currentNode;
    }
    public void addNode(SimpleNodeChain chain){
        root=addNode(root, chain);
    }
    public void traverserPreOrder(AbstractNode root){
        if (root!=null){
            System.out.println(root.simpleNodeChain);
            traverserPreOrder(root.leftNode);
            traverserPreOrder(root.rightNode);
        }
    }

    public AbstractNode getRoot(){
        return root;
    }
}
