package com.example.nftmarket.structs;

import com.example.nftmarket.entity.DinosaurEgg;

public class NormalDinosaurTree {
    AbstractNode root;

    private AbstractNode addNode(AbstractNode currentNode,SimpleNodeChain chain){
        if(currentNode==null){
            return new AbstractNode(chain);
        }
        Node<DinosaurEgg> sourceFatherNode = currentNode.simpleNodeChain.root.fatherNode.sourceNode;
        Node<DinosaurEgg> sourceMotherNode = currentNode.simpleNodeChain.root.motherNode.sourceNode;
        if (sourceFatherNode!=null){
            if (sourceFatherNode.nodeInfo==chain.root.nodeInfo){
                currentNode.leftNode=addNode(currentNode.leftNode,chain);
            }
        }
        if (sourceMotherNode!=null){
            if (sourceMotherNode.nodeInfo==chain.root.nodeInfo){
                currentNode.rightNode=addNode(currentNode.rightNode, chain);
            }
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
