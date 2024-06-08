// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/interfaces/IERC721Receiver.sol";

import { DFCoin } from "./DFCoin.sol";
import { DinosaurEgg } from "./DinosaurEgg.sol";
import { Market } from "./Market.sol";

import "./Storage_Information.sol";

/// 恐龙交易市场
contract DinosaurEggMarket is IERC721Receiver {

    bytes4 internal constant MAGIC_ON_ERC721_RECEIVED = 0x150b7a02;

    address public owner; // 市场所有者
    address public admin; // 市场管理员
    
    DFCoin public DFC;
    DinosaurEgg public DE;

    // ============= 用户恐龙蛋信息 ================
    // 记录用户恐龙蛋的TokenId对应的索引
    mapping(address => mapping(uint256 => uint256)) public userOfDinosaurEggTokenIdToIndex;
    // 记录用户恐龙蛋的index对应的TokenId
    mapping(address => mapping(uint256 => uint256)) public userOfDinosaurEggIndexToTokenId;
    // 记录用户的所有恐龙蛋的 TokenId
    mapping(address => DinosaurEggData[]) public eggAmountOfUser; 
    // 记录用户的某个TokenId对应的恐龙蛋信息
    mapping(address user => mapping(uint256 tokenId => DinosaurEggData)) public userTokenToEgg;

    // 记录上架的所有恐龙蛋
    DinosaurEggData[] public DinosaurEggList;

    // 记录恐龙蛋的卖家
    mapping(uint256 => address) public sellerOfEgg;

    // 记录恐龙蛋Id所在的索引
    mapping(uint256 => uint256) public idToIndexOfEgg;

    // 记录恐龙蛋的TokenId的index
    mapping(uint256 => uint256) public dinosaurEgg_indexToTokenId;


    constructor(address _DFC, address _admin) {
        owner = msg.sender;
        admin = _admin;
        DFC = DFCoin(_DFC);
        DE = new DinosaurEgg(address(this));
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "You are not owner");
        _;
    }

    function _generatingDinosaurEggs(
        address to,
        string memory EggId,
        string memory DinosaurFather,
        string memory DinosaurMother,
        string memory ChildHash,
        string memory EggPhotoURI,
        bool isHatched
    ) internal {
        DinosaurEggData memory egg = DinosaurEggData(
            EggId, DinosaurFather, DinosaurMother, ChildHash, EggPhotoURI, isHatched
        );

        // 为 TokenId 设置uri
        DE.safeMint(to, EggPhotoURI);

        // 更新接收者该TokenId对应的egg信息
        userTokenToEgg[to][DE.nextTokenId() - 1] = egg;

        uint256 length = eggAmountOfUser[to].length;
        // 记录用户恐龙蛋TokenId的index
        userOfDinosaurEggTokenIdToIndex[to][DE.nextTokenId() - 1] = length;

        // 记录用户恐龙蛋index的Token
        userOfDinosaurEggIndexToTokenId[to][length] = DE.nextTokenId() - 1;

        // eggAmountOfUser
        eggAmountOfUser[to].push(egg);
    }

    // 生成恐龙蛋
    function generatingDinosaurEggs(
        address to,
        string memory EggId,
        string memory DinosaurFather,
        string memory DinosaurMother,
        string memory ChildHash,
        string memory EggPhotoURI,
        bool isHatched
    ) public onlyOwner {

        _generatingDinosaurEggs(
            to, 
            EggId, 
            DinosaurFather, 
            DinosaurMother, 
            ChildHash, 
            EggPhotoURI, 
            isHatched
        );

    }

    // 用户间交易恐龙蛋
    // NOTE: from需要提前对该合约授权
    function exchangeDinosaur() external {

        
    }


    function onERC721Received(
        address operator,
        address from,
        uint256 tokenId,
        bytes calldata data
    ) external  returns (bytes4) {
        return MAGIC_ON_ERC721_RECEIVED;
    }
}