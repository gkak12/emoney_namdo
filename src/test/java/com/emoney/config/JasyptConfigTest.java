package com.emoney.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class JasyptConfigTest {

    @Value("${jasypt.encryptor.password}")
    private String encryptorPassword;

    @Test
    void stringEncryptor() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres12#$";

        System.out.println("url: ".concat(jasyptEncoding(url)));
        System.out.println("sa: ".concat(jasyptEncoding(username)));
        System.out.println("password: ".concat(jasyptEncoding(password)));
    }

    private String jasyptEncoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(encryptorPassword);

        return pbeEnc.encrypt(value);
    }

    @Test
    void stringDecryptor() {
        String url = "ENC(w8JYlfb55/e2xRgnXr1I80CiyxyYPRdteF6lVTQH1Jo9yrcr5LzhqQ==)";
        String username = "ENC(uzYY3fWzGX4VSvJcNa7eEw==)";

        System.out.println("url: ".concat(jasyptDecoding(url)));
        System.out.println("username: ".concat(jasyptDecoding(username)));
    }

    private String jasyptDecoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setPassword(encryptorPassword);

        return pbeEnc.decrypt(
                value
                .replace("ENC(", "")
                .replace(")", "")
        );
    }
}
