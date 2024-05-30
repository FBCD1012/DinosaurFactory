package com.example.nftmarket.structs;

import lombok.Data;
import org.bouncycastle.jcajce.provider.symmetric.Noekeon;


//构建泛型类节点实现一个哈希树操作
@Data
public class Node<D> {
    String FatherHash;
    String MotherHash;
    String ChildHash;
    String Type;
    D NodeInfo;
}
