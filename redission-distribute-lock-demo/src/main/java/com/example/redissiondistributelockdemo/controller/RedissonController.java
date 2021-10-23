package com.example.redissiondistributelockdemo.controller;

import com.steak.redissonlock.annotation.U2Lock;
import com.steak.redissonlock.enums.LockType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/10/18:30
 * @Description:
 */
@RestController
public class RedissonController {

    int count = 300;

    @GetMapping("test")
    @U2Lock(lockType = LockType.REENTRANT_LOCK,lockName = "red-lock")
    public int test() throws InterruptedException {
        return count--;
    }
}
