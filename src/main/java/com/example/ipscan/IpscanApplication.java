package com.example.ipscan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IpscanApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpscanApplication.class, args);
    }

}
