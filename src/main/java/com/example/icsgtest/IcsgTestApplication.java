package com.example.icsgtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IcsgTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IcsgTestApplication.class, args);
    }

}
