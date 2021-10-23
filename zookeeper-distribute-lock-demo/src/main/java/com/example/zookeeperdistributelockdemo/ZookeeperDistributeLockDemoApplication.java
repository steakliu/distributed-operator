package com.example.zookeeperdistributelockdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class ZookeeperDistributeLockDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(ZookeeperDistributeLockDemoApplication.class, args);
    }

}
