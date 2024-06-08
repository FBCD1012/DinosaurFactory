// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/interfaces/IERC721Receiver.sol";

import { DFCoin } from "./DFCoin.sol";
import { DinosaurToken } from "./Dinosaur.sol";
import "./Storage_Information.sol";
import "./Market.sol";

import "hardhat/console.sol";

/// 恐龙交易市场
contract DinosaurMarket is IERC721Receiver {

    bytes4 internal constant MAGIC_ON_ERC721_RECEIVED = 0x150b7a02;

    address public owner; // 市场所有者
    address public admin; // 市场管理员
    
    DFCoin public DFC;
    DinosaurToken public DT;
    // Market public market;

 
    // 记录用户恐龙的TokenId对应的索引
    mapping(address => mapping(uint256 => uint256)) public userOfDinosaurTokenIdToIndex;
    // 记录用户恐龙的索引对应的TokenId
    mapping(address => mapping(uint256 => uint256)) public userOfDinosaurIndexToTokenId;
    // 记录用户的所有恐龙的 TokenId
    mapping(address => DinosaurData[]) dinosaurAmountOfUser; 
    // 记录用户的某个TokenId对应的恐龙信息
    mapping(address user => mapping(uint256 tokenId => DinosaurData)) userTokenToDinosaur;


    // 记录恐龙的卖家
    mapping(uint256 => address) public sellerOfDinosaur;
    // 记录上架的所有恐龙
    DinosaurData[] public DinosaurList;
    // 记录恐龙Id所在的索引
    mapping(uint256 => uint256) public TokenIdToIndexOfDinosaur;
    // 记录恐龙的TokenId 的index
    mapping(uint256 => uint256) public dinosaur_indexToTokenId;

    constructor(address _DFC, address _admin) {
        owner = msg.sender; // 市场拥有者
        admin = _admin;     // 使用管理者
        DFC = DFCoin(_DFC); 
        DT = new DinosaurToken(address(this));
        // market = Market(_market);
    }

    modifier onlyOwner() {
        require(msg.sender == owner || msg.sender == admin, "You are not owner");
        _;
    }


    // 添加恐龙
    function _addDinosaur(
        address to,
        string memory DinosaurId,
        string memory DinosaurSex,
        string memory DinosaurType,
        bool isBreeding,
        string memory DinosaurColor,
        string memory DinosaurRarity,
        string memory DinosaurPhotoUri,
        uint256 DinosaurPrice,
        string memory SourceHash,
        bool isSale
    ) internal {

        DinosaurData memory dinosaur = DinosaurData(
            DinosaurId,
            DinosaurSex,
            DinosaurType,
            isBreeding,
            DinosaurColor,
            DinosaurRarity,
            DinosaurPhotoUri,
            DinosaurPrice,
            SourceHash,
            isSale
        );

        // 为 TokenId 设置uri
        DT.safeMint(to, DinosaurPhotoUri);

        // 更新接收者该TokenId对应的dinosaur信息
        userTokenToDinosaur[to][DT.nextTokenId() - 1] = dinosaur;

        uint256 length = dinosaurAmountOfUser[to].length;
        // 记录用户恐龙TokenId的索引
        userOfDinosaurTokenIdToIndex[to][DT.nextTokenId() - 1] = length;

        // 记录用户恐龙列表中索引对应的TokenId
        userOfDinosaurIndexToTokenId[to][length] = DT.nextTokenId() - 1;

        // 添加进入用户恐龙的列表中
        dinosaurAmountOfUser[to].push(dinosaur);

    }

    // 添加恐龙
    function addDinosaur(
        address to,
        string memory DinosaurId,
        string memory DinosaurSex,
        string memory DinosaurType,
        bool isBreeding,
        string memory DinosaurColor,
        string memory DinosaurRarity,
        string memory DinosaurPhotoUri,
        uint256 DinosaurPrice,
        string memory SourceHash,
        bool isSale
    ) public onlyOwner {

        // 直接调用内部函数即可，解耦
        _addDinosaur(
            to,
            DinosaurId,
            DinosaurSex,
            DinosaurType,
            isBreeding,
            DinosaurColor,
            DinosaurRarity,
            DinosaurPhotoUri,
            DinosaurPrice,
            SourceHash,
            isSale
        );
    }

    /// ------------ 市场信息数据 ---------------
    // 用户上架自己的恐龙NFT
    function onERC721Received(
        address operator,
        address from,
        uint256 tokenId,
        bytes calldata data
    ) external  returns (bytes4) {

        // address owner = DT.ownerOf(tokenId);
        // require(DT.ownerOf(tokenId) == tx.origin, "You have not the token");

        // 获取恐龙详情
        DinosaurData storage dinosaur = userTokenToDinosaur[tx.origin][tokenId];
        require(!dinosaur.isSale, "You are saling");
        dinosaur.isSale = true;

        // 记录卖家信息
        sellerOfDinosaur[tokenId] = tx.origin;
        // 转移该NFT的所有权，转移到Market手上
        // DT.safeTransferFrom(address(this), address(market), tokenId);
        // 记录该TokeId的索引
        TokenIdToIndexOfDinosaur[tokenId] = DinosaurList.length;
        // 记录该索引的tokenId
        dinosaur_indexToTokenId[DinosaurList.length] = tokenId;
        // 添加到上架列表中
        DinosaurList.push(dinosaur);

        return MAGIC_ON_ERC721_RECEIVED;
    }

    // 用户下架自己的恐龙NFT
    function cancelDinosaurSale(uint256 tokenId) external {

        address seller = sellerOfDinosaur[tokenId];
        // 判断调用者是不是卖家
        require(seller == tx.origin, "You are not seller.");

        // 修改恐龙的售卖状态
        uint256 index = TokenIdToIndexOfDinosaur[tokenId];
        DinosaurData storage dinosaur = DinosaurList[index];
        dinosaur.isSale = false;

        // 退还NFT
        DT.safeTransferFrom(address(this), tx.origin, tokenId);

        // 更新数据
        removeDinosaurList(tokenId);

        // 删除售卖者
        delete sellerOfDinosaur[tokenId];

    }

    // 移除上架恐龙列表元素
    function removeDinosaurList(uint256 tokenId) internal {

        uint256 index = TokenIdToIndexOfDinosaur[tokenId];
        uint256 last = DinosaurList.length - 1;
        if (index != last) {
            DinosaurData storage dinosaur = DinosaurList[last];
            DinosaurList[index] = dinosaur;
            uint256 _tokenId = dinosaur_indexToTokenId[last];
            TokenIdToIndexOfDinosaur[_tokenId] = index;
        }
        DinosaurList.pop();
        delete sellerOfDinosaur[tokenId];
        delete TokenIdToIndexOfDinosaur[tokenId];
    }

    // 购买恐龙NFT
    function purchaseDinosaur(uint256 tokenId) external payable {

        // 从上架恐龙NFT中获取该NFT的index
        uint256 index = TokenIdToIndexOfDinosaur[tokenId];
        DinosaurData storage dinosaur = DinosaurList[index];
        require(msg.value >= dinosaur.DinosaurPrice, "You should pay more money.");

        // 获取TokenId的售卖者
        address seller = sellerOfDinosaur[tokenId];

        // 获取该恐龙 tokenId所对应的 index
        uint256 _index = userOfDinosaurTokenIdToIndex[seller][tokenId];

        // 转移市场中恐龙NFT的所有权
        DT.safeTransferFrom(address(this), tx.origin, tokenId);
        dinosaur.isSale = false; // 取消售卖状态

        // 将该恐龙的所有权移交给 tx.origin
        userTokenToDinosaur[tx.origin][tokenId] = dinosaur;

        removeUserDinosaur(seller, tokenId);

        userOfDinosaurTokenIdToIndex[tx.origin][tokenId] = dinosaurAmountOfUser[tx.origin].length;
        userOfDinosaurIndexToTokenId[tx.origin][dinosaurAmountOfUser[tx.origin].length];
        dinosaurAmountOfUser[msg.sender].push(dinosaur);

        // 删除市场的所有映射关系
        // 删除售卖者
        delete sellerOfDinosaur[tokenId];

        // 删除 恐龙所有者者关系
        // 删除该用户的恐龙信息
        delete userTokenToDinosaur[seller][tokenId];
        delete userOfDinosaurTokenIdToIndex[seller][tokenId];
        delete userOfDinosaurIndexToTokenId[seller][_index];
    }

    // 移除用户拥有的恐龙
    function removeUserDinosaur(address user, uint256 tokenId) internal {

        uint256 index = userOfDinosaurTokenIdToIndex[user][tokenId];
        uint256 last = dinosaurAmountOfUser[user].length - 1;

        if (index != last) {
            DinosaurData storage dinosaur = dinosaurAmountOfUser[user][last];
            dinosaurAmountOfUser[user][index] = dinosaur;
            uint256 _tokenId = dinosaur_indexToTokenId[last];
            TokenIdToIndexOfDinosaur[_tokenId] = index;
        }

        dinosaurAmountOfUser[user].pop();
        // 移除用户TokenId的索引
        delete dinosaur_indexToTokenId[tokenId];
        delete TokenIdToIndexOfDinosaur[tokenId];
    }

    // 用户间进行恐龙交易
    // NOTE: from需要提前对该合约授权
    function exchangeDinosaur(address from, uint256 tokenId) external payable {

        address owner = DT.ownerOf(tokenId);
        DinosaurData storage dinosaur = userTokenToDinosaur[owner][tokenId];
        require(msg.value >= dinosaur.DinosaurPrice, "You need pay more money.");

        // 将tokenId转移给调用者
        DT.safeTransferFrom(owner, tx.origin, tokenId);

        // 更新NFT接收者 tokenId和index之间的关系
        userOfDinosaurTokenIdToIndex[tx.origin][tokenId] = dinosaurAmountOfUser[tx.origin].length;
        userOfDinosaurIndexToTokenId[tx.origin][dinosaurAmountOfUser[tx.origin].length] = tokenId;

        // 更新用户的恐龙信息
        userTokenToDinosaur[tx.origin][tokenId] = dinosaur;
        dinosaurAmountOfUser[tx.origin].push(dinosaur);

        /// ========= 删除就用户的信息 ==========
        uint256 index = userOfDinosaurTokenIdToIndex[owner][tokenId];
        delete userOfDinosaurTokenIdToIndex[owner][tokenId];
        delete userOfDinosaurIndexToTokenId[owner][index];
        // 数组操作
        uint256 last = dinosaurAmountOfUser[owner].length - 1;
        if (index != last) {
            DinosaurData storage dinosaur = dinosaurAmountOfUser[owner][last];
            dinosaurAmountOfUser[owner][index] = dinosaur;
        }
        dinosaurAmountOfUser[owner].pop();
        delete userTokenToDinosaur[owner][tokenId];
    }

    // 返回市场上所有上架的 DinosaurNFT
    function getAllDinosaurs() external view returns(DinosaurData[] memory) {
        return DinosaurList;
    }

    /// ------------ 个人信息数据 ---------------

    // 查询用户代币数量
    function getUserTokenAmount(address user) external view returns (uint256 amount) {
        amount = DFC.balanceOf(user);
    }

    // 获取用户所有的恐龙
    function getUserDinosaurs(address user) public view returns(DinosaurData[] memory) {
        return dinosaurAmountOfUser[user];
    }

    // 根据用户以及其TokenId查询恐龙
    function getDinosaurFromTokenId(address user, uint256 tokenId) public view returns (DinosaurData memory) {
        return userTokenToDinosaur[user][tokenId];
    }   
}