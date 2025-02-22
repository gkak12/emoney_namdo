package com.emoney.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

public class JasyptConfigTest {

    @Test
    void stringEncryptor() {
        String url = "jdbc:h2:mem:meeting";
        String username = "sa";

        System.out.println("url: ".concat(jasyptEncoding(url)));
        System.out.println("username: ".concat(jasyptEncoding(username)));
    }

    private String jasyptEncoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(System.getenv("JASYPT_KEY"));

        return pbeEnc.encrypt(value);
    }

    @Test
    void stringDecryptor() {
        String url = "fwst664/lJPSUzfOpnQyjLRwXi0KgGpvMRQG/eQ9Vz4=";
        String username = "vs4ggM5iCn/MpoROqbLxjA==";

        System.out.println("url: ".concat(jasyptDecoding(url)));
        System.out.println("username: ".concat(jasyptDecoding(username)));
    }

    private String jasyptDecoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(System.getenv("JASYPT_KEY"));

        return pbeEnc.decrypt(
                value
                .replace("ENC(", "")
                .replace(")", "")
        );
    }
}
