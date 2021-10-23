package com.example.redissiondistributelockdemo;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedissionDistributeLockDemoApplicationTests {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void contextLoads() throws InterruptedException {
        RLock lock = redissonClient.getFairLock("liu");
        boolean b = lock.tryLock(3, 20, TimeUnit.SECONDS);
        System.out.println(b);
    }

}
