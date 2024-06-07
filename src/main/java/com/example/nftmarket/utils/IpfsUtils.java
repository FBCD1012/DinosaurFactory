package com.example.nftmarket.utils;

import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IpfsUtils {
    static IPFS ipfs=new IPFS("/ip4/127.0.0.1/tcp/8080");

    static String pathName="D:\\SpringCloudAlibaba\\NFT-Market\\src\\main\\resources\\static\\images";

    public static void download(String hash) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] data = ipfs.cat(filePointer);
        if(data != null){
            File file = new File(pathName+hash+".jpeg");
            if(file.exists()){
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data,0,data.length);
            fos.flush();
            fos.close();
        }
    }
}
