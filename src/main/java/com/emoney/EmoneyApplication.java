package com.emoney;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.emoney")
public class EmoneyApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmoneyApplication.class, args);
    }
}