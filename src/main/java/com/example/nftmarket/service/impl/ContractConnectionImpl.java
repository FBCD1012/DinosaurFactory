package com.example.nftmarket.service.impl;

import com.example.nftmarket.service.ContractConnection;
import com.example.nftmarket.utils.contractUtils.contractsClass.DFC;
import com.example.nftmarket.utils.contractUtils.contractsClass.DinosaurEggMarket;
import com.example.nftmarket.utils.contractUtils.contractsClass.DinosaurMarket;
import com.example.nftmarket.utils.contractUtils.contractsClass.Market;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;



//可以算作初始化我们的各个合约的对象，然后我们再进行的相关操作理解
@Service
public class ContractConnectionImpl implements ContractConnection {

    @Value("${contract.chain_id}")
    private static Integer chainId;
    @Value("${contract.network_url}")
    private static String netWorkUrl;
    @Value("${contract.wallet_key}")
    private static String walletKey;
    @Value("${contract.dfc_address}")
    private static String DFC_ad;
    @Value("${contract.market_address}")
    private static String market_ad;
    @Value("${contract.dm_address}")
    private static String dm_ad;
    @Value("${contract.dem_address}")
    private static String dem_ad;

    @SneakyThrows
    @Override
    public DFC connectTheDFC() {
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,chainId);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        return DFC.load(DFC_ad,web3,
                transactionManager,new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
    }

    @SneakyThrows
    @Override
    public Market connectTheMarket() {
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,chainId);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        return Market.load(market_ad,web3,
                transactionManager,new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
    }

    //可以算作初始化我们的各个合约的对象，然后我们再进行的相关操作理解
    @SneakyThrows
    @Override
    public DinosaurMarket connectTheDinosaurMarket() {
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,chainId);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        return DinosaurMarket.load(dm_ad,web3,
                transactionManager,new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
    }

    @SneakyThrows
    @Override
    public DinosaurEggMarket connectTheDinosaurEggMarket() {
        Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
        Credentials credentials = Credentials.create(walletKey);
        TransactionManager transactionManager = new RawTransactionManager(
                web3, credentials,chainId);
        BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
        return DinosaurEggMarket.load(dem_ad,web3,
                transactionManager,new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
    }
}
