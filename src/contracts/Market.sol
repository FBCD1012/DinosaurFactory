// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/interfaces/IERC721Receiver.sol";

import { DFCoin } from "./DFCoin.sol";
import { DinosaurToken } from "./Dinosaur.sol";
import { DinosaurMarket } from "./DinosaurMarket.sol";
import { DinosaurEggMarket } from "./DinosaurEggMarket.sol";
import { DinosaurEgg } from "./DinosaurEgg.sol";

import "./Storage_Information.sol";


contract Market is IERC721Receiver {

    bytes4 internal constant MAGIC_ON_ERC721_RECEIVED = 0x150b7a02;

    address public owner; // 市场所有者
    address public admin; // 市场管理员
    DFCoin public DFC;
    DinosaurToken public DT;
    DinosaurEgg public DE;
    DinosaurMarket public DM;
    DinosaurEggMarket public DEM; // 恐龙蛋市场

    // 记录恐龙蛋孵化的时间
    mapping(uint256 tokenId => uint256 startTime) public hatchTime;


    constructor(address _DFC, address _admin) {
        owner = msg.sender;
        DFC = DFCoin(_DFC);
        admin = _admin;
    }
    
    modifier onlyOwner() {
        require(msg.sender == owner || msg.sender == admin, "You are not owner");
        _;
    }

    function init(address _DM, address _DEM) external onlyOwner {
        DM = DinosaurMarket(_DM);
        DT = DM.DT();
        DEM = DinosaurEggMarket(_DEM);
        DE = DEM.DE();
    }


    /*
        交配函数

        逻辑：判断恐龙的性别，给用户生成一个龙蛋
    */
    // function mate(uint256 dinosaurA, 
    //     uint256 dinosaurB,
    //     string memory EggId,
    //     string memory DinosaurFather,
    //     string memory DinosaurMother,
    //     string memory ChildHash,
    //     string memory EggPhotoURI,
    //     bool isHatched
    // ) external {

    //     // 判断所有权
    //     require(
    //         DT.ownerOf(dinosaurA) == msg.sender 
    //         && DT.ownerOf(dinosaurB) == msg.sender, 
    //         "You are not owner of egg"
    //     );

    //     // 判断性别
    //     DinosaurData memory da = DM.getDinosaurFromTokenId(msg.sender, dinosaurA);
    //     DinosaurData memory db = DM.getDinosaurFromTokenId(msg.sender, dinosaurB);
    //     require(keccak256(abi.encodePacked(da.DinosaurSex)) == keccak256(abi.encodePacked(db.DinosaurSex)));

    //     DEM.generatingDinosaurEggs(
    //         msg.sender, 
    //         EggId, 
    //         DinosaurFather, 
    //         DinosaurMother, 
    //         ChildHash, 
    //         EggPhotoURI, 
    //         isHatched
    //     );
        
    // }

    // 孵化函数
    // function hatch(
    //     uint256 tokenId,
    //     string memory DinosaurId,
    //     string memory DinosaurSex,
    //     string memory DinosaurType,
    //     bool isBreeding,
    //     string memory DinosaurColor,
    //     string memory DinosaurRarity,
    //     string memory DinosaurPhotoUri,
    //     uint256 DinosaurPrice,
    //     string memory SourceHash,
    //     bool isSale        
    // ) external {

    //     // 判断恐龙蛋的所有者
    //     require(msg.sender == DE.ownerOf(tokenId), "You dont have the egg.");

    //     // 判断恐龙蛋是否孵化
    //     DinosaurEggData memory egg = DEM.getUserEggFromTokenId(msg.sender,tokenId);
    //     require(!egg.isHatched, "You have hatched...");

    //     // 这表示还未进行过孵化
    //     if (hatchTime[tokenId] == 0) {
    //         hatchTime[tokenId] = block.timestamp; // 设置当前时间戳
    //         return; // 直接结束
    //     }

    //     // 判断是否孵化过三天
    //     require(hatchTime[tokenId] - block.timestamp >= 3 days, "The egg need more time to hatch.");

    //     // 更改恐龙蛋的属性
    //     DEM.setEggIsHatched(msg.sender, tokenId); // 表示已经孵化
        
    //     DM.addDinosaur(
    //         msg.sender,
    //         DinosaurId,
    //         DinosaurSex,
    //         DinosaurType,
    //         isBreeding,
    //         DinosaurColor,
    //         DinosaurRarity,
    //         DinosaurPhotoUri,
    //         DinosaurPrice,
    //         SourceHash,
    //         isSale
    //     );
        
    // }

    function onERC721Received(
        address operator,
        address from,
        uint256 tokenId,
        bytes calldata data
    ) external returns (bytes4) {
        return MAGIC_ON_ERC721_RECEIVED;
    }
}