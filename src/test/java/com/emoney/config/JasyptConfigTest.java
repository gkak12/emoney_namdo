package com.emoney.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptConfigTest {

    @Value("${jasypt.encryptor.password}")
    private String encryptorPassword;

    @Test
    void stringEncryptor() {
        String url = "jdbc:h2:mem:emoney_namdo";
        String username = "sa";

        System.out.println("url: ".concat(jasyptEncoding(url)));
        System.out.println("sa: ".concat(jasyptEncoding(username)));
    }

    private String jasyptEncoding(String value) {
        String key = encryptorPassword;
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}
