package com.example.nftmarket.service;

import com.example.nftmarket.utils.contractUtils.contractsClass.DFC;
import com.example.nftmarket.utils.contractUtils.contractsClass.DinosaurEggMarket;
import com.example.nftmarket.utils.contractUtils.contractsClass.DinosaurMarket;
import com.example.nftmarket.utils.contractUtils.contractsClass.Market;

public interface ContractConnection {
    DFC connectTheDFC();
    Market connectTheMarket();
    DinosaurMarket connectTheDinosaurMarket();
    DinosaurEggMarket connectTheDinosaurEggMarket();
}
