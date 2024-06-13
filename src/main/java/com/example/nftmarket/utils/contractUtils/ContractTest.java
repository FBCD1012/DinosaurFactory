package com.example.nftmarket.utils.contractUtils;

import com.example.nftmarket.utils.contractUtils.contractsClass.DFC;
import com.example.nftmarket.utils.contractUtils.contractsClass.DinosaurMarket;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class ContractTest {
    private static ContractTest contractUtil = new ContractTest();
    private ContractTest(){
    }
    public static ContractTest getInstance() {
        return contractUtil;
    }
    // 网络地址
    private static String netWorkUrl = "HTTP://127.0.0.1:8545";
    // 钱包私钥
    private static String walletKey = "0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80";
    // 合约地址
    private static String contractAddress = "0xe7f1725E7734CE288F8367e1Bb143E90bb3F0512";

    public static void useContract(){
        try {
            //连接对应节点
            Web3j web3 = Web3j.build(new HttpService(netWorkUrl));
            Credentials credentials = Credentials.create(walletKey);
            TransactionManager transactionManager = new RawTransactionManager(
                    web3, credentials,3);
            BigInteger gasPrice = web3.ethGasPrice().send().getGasPrice();
            DinosaurMarket contract = DinosaurMarket.load(contractAddress,web3,
                    transactionManager,new StaticGasProvider(gasPrice, Contract.GAS_LIMIT));
//            //调用合约方法
            RemoteFunctionCall<String> setWord = contract.admin();
            System.out.println("合约返回值输出："+setWord.send());


//            RemoteFunctionCall<BigInteger> bigIntegerRemoteFunctionCall = contract.balanceOf("0x657cF811dBB5Ebdd83ba0Aa616A5a097f3C5387E");
//            BigInteger send1 = bigIntegerRemoteFunctionCall.send();
//            System.out.println("余额："+send1);

//            RemoteCall<TransactionReceipt> setWord = contract.newGreeting("hello world");
//            TransactionReceipt transactionReceipt = setWord.send();
//            String transactionHash = transactionReceipt.getTransactionHash();
//            System.out.println(transactionHash);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ContractTest instance = ContractTest.getInstance();
        instance.useContract();
    }


}