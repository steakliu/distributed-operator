package com.steak.redissonlock.service;

import com.steak.redissonlock.enums.LockType;
import com.steak.redissonlock.exception.LockFailException;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/10/23:53
 * @Description:
 */
public class RedissonLockServiceImpl implements RedissonLockService {

    RedissonClient redissonClient;

    RLock lock;

    @Override
    public void lock(String key, LockType lockType, long leaseTime, long waitTime, TimeUnit timeUnit) {
        if (!lockType.equals(LockType.RED_LOCK)){
            lock = getLock(lockType, key);
            try {
                boolean b = lock.tryLock(waitTime, leaseTime, timeUnit);
                if (!b) {
                    throw new LockFailException("lock fail");
                }
            } catch (Exception e) {
                throw new LockFailException("lock exception");
            } finally {
                lock.unlock();
            }
        }
        else {
            redLock(key , waitTime , leaseTime , timeUnit);
        }

    }

    @Override
    public void unlock() {
        lock.unlock();
    }

    public RLock getLock(LockType lockType, String key) {
        RLock rLock;
        switch (lockType) {
            case FAIR_LOCK:
                rLock =  redissonClient.getFairLock(key);
                break;
            case MULTI_LOCK:
                rLock = redissonClient.getMultiLock();
                break;
            case READ_LOCK:
                RReadWriteLock readLock = redissonClient.getReadWriteLock(key);
                rLock =  readLock.readLock();
                break;
            case WRITE_LOCK:
                RReadWriteLock writeLock = redissonClient.getReadWriteLock(key);
                rLock =  writeLock.writeLock();
                break;
            default:
                rLock = redissonClient.getLock(key);
        }
        return rLock;
    }

    public void redLock(String key , long waitTime, long leaseTime, TimeUnit timeUnit){
        List<RedissonClient> list = RedLockCache.list();
        List<RLock> locks = new ArrayList<>();
        list.forEach(item -> {
            locks.add(item.getLock(key));
        });
        RLock[] rLocks = new RLock[locks.size()];
        locks.toArray(rLocks);
        RedissonRedLock redLock = new RedissonRedLock(rLocks);
        try {
            boolean b = redLock.tryLock(waitTime, leaseTime, timeUnit);
            if (!b){
                throw new LockFailException("red lock locking fail");
            }
        }catch (Exception e){
            throw new LockFailException("red lock locking exception");
        }finally {
            redLock.unlock();
        }
    }



    public RedissonLockServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
}
