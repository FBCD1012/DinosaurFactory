// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;


// 恐龙结构体
struct DinosaurData {

    // 哈希值
    string DinosaurId;
    string DinosaurSex;
    string DinosaurType;
    bool isBreeding;

    // details
    string DinosaurColor;
    string DinosaurRarity;
    string DinosaurPhotoUri;
    uint256 DinosaurPrice;

    // 恐龙卵生哈希
    string SourceHash;

    // 售卖状态
    bool isSale;
}


// 恐龙蛋
struct DinosaurEggData {
    string EggId;
    string DinosaurFather;
    string DinosaurMother;
    string ChildHash;
    string EggPhotoURI;
    bool isHatched;
}
