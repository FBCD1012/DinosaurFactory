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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class DinosaurEggMarket extends Contract {
    public static  String BINARY;

    static {
        BINARY=  SolidityAutoUtils.getInstance().getBytecodeJson();
    }

    public static final String FUNC_DE = "DE";

    public static final String FUNC_DFC = "DFC";

    public static final String FUNC_ADMIN = "admin";

    public static final String FUNC_EXCHANGEDINOSAUR = "exchangeDinosaur";

    public static final String FUNC_GENERATINGDINOSAUREGGS = "generatingDinosaurEggs";

    public static final String FUNC_GETEGGLISTOFUSER = "getEggListOfUser";

    public static final String FUNC_GETUSEREGGFROMTOKENID = "getUserEggFromTokenId";

    public static final String FUNC_ONERC721RECEIVED = "onERC721Received";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SETEGGISHATCHED = "setEggIsHatched";

    @Deprecated
    protected DinosaurEggMarket(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DinosaurEggMarket(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DinosaurEggMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DinosaurEggMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> DE() {
        final Function function = new Function(FUNC_DE, 
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

    public RemoteFunctionCall<String> admin() {
        final Function function = new Function(FUNC_ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> exchangeDinosaur(BigInteger tokenId) {
        final Function function = new Function(
                FUNC_EXCHANGEDINOSAUR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> generatingDinosaurEggs(String to, String EggId, String DinosaurFather, String DinosaurMother, String ChildHash, String EggPhotoURI, Boolean isHatched) {
        final Function function = new Function(
                FUNC_GENERATINGDINOSAUREGGS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.Utf8String(EggId), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurFather), 
                new org.web3j.abi.datatypes.Utf8String(DinosaurMother), 
                new org.web3j.abi.datatypes.Utf8String(ChildHash), 
                new org.web3j.abi.datatypes.Utf8String(EggPhotoURI), 
                new org.web3j.abi.datatypes.Bool(isHatched)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getEggListOfUser(String user) {
        final Function function = new Function(FUNC_GETEGGLISTOFUSER, 
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

    public RemoteFunctionCall<String> getUserEggFromTokenId(String user, BigInteger tokenId) {
        final Function function = new Function(FUNC_GETUSEREGGFROMTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setEggIsHatched(String user, BigInteger tokenId) {
        final Function function = new Function(
                FUNC_SETEGGISHATCHED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DinosaurEggMarket load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DinosaurEggMarket(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DinosaurEggMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DinosaurEggMarket(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DinosaurEggMarket load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DinosaurEggMarket(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DinosaurEggMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DinosaurEggMarket(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DinosaurEggMarket> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurEggMarket.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<DinosaurEggMarket> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurEggMarket.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<DinosaurEggMarket> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurEggMarket.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<DinosaurEggMarket> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _DFC, String _admin, String _market) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _DFC), 
                new org.web3j.abi.datatypes.Address(160, _admin), 
                new org.web3j.abi.datatypes.Address(160, _market)));
        return deployRemoteCall(DinosaurEggMarket.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
