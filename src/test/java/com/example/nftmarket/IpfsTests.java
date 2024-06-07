package com.example.nftmarket;


import com.example.nftmarket.utils.IpfsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class IpfsTests {
    IpfsUtils ipfs;
    @Test
    void getTheIpfs() throws IOException {
        ipfs.download("QmUcwtN28cPWedFo6artyxZozGfp9U4j6zTVp9rEkewE39");
    }
}
