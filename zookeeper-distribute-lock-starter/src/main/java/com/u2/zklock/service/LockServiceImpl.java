package com.u2.zklock.service;

import com.u2.zklock.constants.ZkConstant;
import com.u2.zklock.enums.LockType;
import com.u2.zklock.exception.LockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.*;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/15/11:14
 * @Description:
 */
@Slf4j
public class LockServiceImpl implements LockService{

    CuratorFramework curatorFramework;

    InterProcessLock lock;

    @Override
    public void lock(long requireTime, TimeUnit unit , String lockName , LockType lockType) {
        lock = getLockType(lockType, lockName);
        try {
            boolean b = lock.acquire(requireTime, unit);
            if (!b){
                throw new LockException("lock fail");
            }
        }catch (Exception e){
            throw new LockException("lock exception");
        }
    }

    @Override
    public void unlock() {
        try {
            if (lock.isAcquiredInThisProcess()) {
                lock.release();
            }
        } catch (Exception e) {
            throw new RuntimeException("release lock Exception");
        }
    }

    public InterProcessLock getLockType(LockType lockType,String lockName){
        String path = ZkConstant.LOCK_ROOT+lockName;
        InterProcessLock interProcessLock;
        switch (lockType){
            case MULTI_LOCK:
                interProcessLock = new InterProcessMultiLock(curatorFramework, Collections.singletonList(path));
                break;
            case WRITE_LOCK:
                InterProcessReadWriteLock writeLock = new InterProcessReadWriteLock(curatorFramework, path);
                interProcessLock = writeLock.writeLock();
                break;
            case READ_LOCK:
                InterProcessReadWriteLock readLock = new InterProcessReadWriteLock(curatorFramework, path);
                interProcessLock = readLock.readLock();
                break;
            case SEMAPHORE_MUTEX_LOCK:
                interProcessLock = new InterProcessSemaphoreMutex(curatorFramework,path);
                break;
            default:
                interProcessLock = new InterProcessMutex(curatorFramework, path);
        }
        return interProcessLock;
    }

    public LockServiceImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }
}
