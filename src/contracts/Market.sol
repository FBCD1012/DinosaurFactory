// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/interfaces/IERC721Receiver.sol";

import { DFCoin } from "./DFCoin.sol";
import { DinosaurToken } from "./Dinosaur.sol";
import { DinosaurMarket } from "./DinosaurMarket.sol";
import { DinosaurEggMarket } from "./DinosaurEggMarket.sol";

import "./Storage_Information.sol";

contract Market is IERC721Receiver {

    bytes4 internal constant MAGIC_ON_ERC721_RECEIVED = 0x150b7a02;

    address public owner; // 市场所有者
    address public admin; // 市场管理员
    DFCoin public DFC;
    DinosaurToken public DT;
    // DinosaurEgg public DE;
    DinosaurEggMarket public DEM; // 恐龙市场


    /// ============= mapping ============
    // mapping()

    constructor(address _DFC, address _admin) {
        owner = msg.sender;
        DFC = DFCoin(_DFC);
        admin = _admin;
        DEM = new DinosaurEggMarket(_DFC, _admin);
    }

    // 恐龙交配
    function mate(uint256 tokenIdA, uint256 tokenIdB) external {

    }

        // // 孵化函数，这里需要对接
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
    //     DinosaurEggData storage egg = userTokenToEgg[msg.sender][tokenId];
    //     require(!egg.isHatched, "You have hatched...");

    //     // 更改恐龙蛋的属性
    //     egg.isHatched = true; // 表示已经孵化
        
    //     _addDinosaur(
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