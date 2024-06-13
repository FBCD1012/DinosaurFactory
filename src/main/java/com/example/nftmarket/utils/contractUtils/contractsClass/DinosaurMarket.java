package com.example.nftmarket.utils.contractUtils.contractsClass;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import com.example.nftmarket.utils.contractUtils.SolidityAutoUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple10;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 5.0.0.
 */
@SuppressWarnings("rawtypes")
public class DinosaurMarket extends Contract {
    public static  String BINARY;

    static {
        BINARY=  SolidityAutoUtils.getInstance().getBytecodeJson();
    }


    public static final String FUNC_ADDDINOSAUR = "addDinosaur";

    public static final String FUNC_CANCELDINOSAURSALE = "cancelDinosaurSale";

    public static final String FUNC_EXCHANGEDINOSAUR = "exchangeDinosaur";

    public static final String FUNC_GETALLDINOSAURS = "getAllDinosaurs";

    public static final String FUNC_ONERC721RECEIVED = "onERC721Received";

    public static final String FUNC_PURCHASEDINOSAUR = "purchaseDinosaur";

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_DFC = "DFC";

    public static final String FUNC_DINOSAUR_INDEXTOTOKENID = "dinosaur_indexToTokenId";

    public static final String FUNC_DINOSAURLIST = "DinosaurList";

    public static final String FUNC_DT = "DT";

    public static final String FUNC_GETDINOSAURFROMTOKENID = "getDinosaurFromTokenId";

    public static final String FUNC_GETUSERDINOSAURS = "getUserDinosaurs";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SELLEROFDINOSAUR = "sellerOfDinosaur";

    public static final String FUNC_TOKENIDTOINDEXOFDINOSAUR = "TokenIdToIndexOfDinosaur";

    public static final String FUNC_USEROFDINOSAURINDEXTOTOKENID = "userOfDinosaurIndexToTokenId";

    public static final String FUNC_USEROFDINOSAURTOKENIDTOINDEX = "userOfDinosaurTokenIdToIndex";

    @Deprecated
    protected DinosaurMarket(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DinosaurMarket(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DinosaurMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DinosaurMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addDinosaur(String to, String DinosaurId, String DinosaurSex, String DinosaurType, Boolean isBreeding, String DinosaurColor, String DinosaurRarity, String DinosaurPhotoUri, BigInteger DinosaurPrice, String SourceHash, Boolean isSale) {
        final Function function = new Function(
                FUNC_ADDDINOSAUR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurId), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurSex), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurType), 
                new org.web3j.abi.datatypes.Bool(isBreeding), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurColor), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurRarity), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurPhotoUri), 
                new org.web3j.abi.datatypes.generated.Uint256(DinosaurPrice), 
                new org.web3j.abi.datatypes.Utf8String(SourceHash), 
                new org.web3j.abi.datatypes.Bool(isSale)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelDinosaurSale(BigInteger tokenId) {
        final Function function = new Function(
                FUNC_CANCELDINOSAURSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> exchangeDinosaur(String from, BigInteger tokenId) {
        final Function function = new Function(
                FUNC_EXCHANGEDINOSAUR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getAllDinosaurs() {
        final Function function = new Function(
                FUNC_GETALLDINOSAURS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> onERC721Received(String operator, String from, BigInteger tokenId, byte[] data) {
        final Function function = new Function(
                FUNC_ONERC721RECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> purchaseDinosaur(BigInteger tokenId) {
        final Function function = new Function(
                FUNC_PURCHASEDINOSAUR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> admin() {
        final Function function = new Function(FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> DFC() {
        final Function function = new Function(FUNC_DFC, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> dinosaur_indexToTokenId(BigInteger param0) {
        final Function function = new Function(FUNC_DINOSAUR_INDEXTOTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple10<String, String, String, Boolean, String, String, String, BigInteger, String, Boolean>> DinosaurList(BigInteger param0) {
        final Function function = new Function(FUNC_DINOSAURLIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple10<String, String, String, Boolean, String, String, String, BigInteger, String, Boolean>>(function,
                new Callable<Tuple10<String, String, String, Boolean, String, String, String, BigInteger, String, Boolean>>() {
                    @Override
                    public Tuple10<String, String, String, Boolean, String, String, String, BigInteger, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple10<String, String, String, Boolean, String, String, String, BigInteger, String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (String) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (String) results.get(8).getValue(), 
                                (Boolean) results.get(9).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> DT() {
        final Function function = new Function(FUNC_DT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getDinosaurFromTokenId(String user, BigInteger tokenId) {
        final Function function = new Function(FUNC_GETDINOSAURFROMTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getUserDinosaurs(String user) {
        final Function function = new Function(FUNC_GETUSERDINOSAURS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> sellerOfDinosaur(BigInteger param0) {
        final Function function = new Function(FUNC_SELLEROFDINOSAUR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> TokenIdToIndexOfDinosaur(BigInteger param0) {
        final Function function = new Function(FUNC_TOKENIDTOINDEXOFDINOSAUR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> userOfDinosaurIndexToTokenId(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_USEROFDINOSAURINDEXTOTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> userOfDinosaurTokenIdToIndex(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_USEROFDINOSAURTOKENIDTOINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DinosaurMarket load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DinosaurMarket(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DinosaurMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DinosaurMarket(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DinosaurMarket load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DinosaurMarket(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DinosaurMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DinosaurMarket(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DinosaurMarket> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurMarket.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<DinosaurMarket> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurMarket.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<DinosaurMarket> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurMarket.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<DinosaurMarket> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurMarket.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
