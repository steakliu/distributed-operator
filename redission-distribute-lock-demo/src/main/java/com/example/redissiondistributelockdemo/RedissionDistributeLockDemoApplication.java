package com.example.redissiondistributelockdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class RedissionDistributeLockDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(RedissionDistributeLockDemoApplication.class, args);
    }

}
