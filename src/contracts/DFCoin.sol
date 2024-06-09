// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract DFCoin is ERC20, Ownable {

    // paramater: admin管理员
    constructor(address admin)
        ERC20("DFCoin", "DFC")
        Ownable(admin)
    {}

    // 为各个用户铸造代币
    function mint(address to, uint256 amount) public onlyOwner {
        _mint(to, amount);
    }
}