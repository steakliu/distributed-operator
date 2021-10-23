package com.steak.redissonlock.service;

import com.steak.redissonlock.enums.LockType;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/10/23:46
 * @Description:
 */
public interface RedissonLockService {

    void lock(String key , LockType lockType, long leaseTime , long waitTime , TimeUnit timeUnit);

    void unlock();

}
