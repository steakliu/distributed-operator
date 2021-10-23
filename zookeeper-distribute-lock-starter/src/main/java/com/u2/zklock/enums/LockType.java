package com.u2.zklock.enums;

/**
 * 锁类型
 */
public enum LockType {
    MULTI_LOCK("multi_lock"),
    MUTEX_LOCK("mutex_lock"),
    READ_LOCK("read_lock"),
    WRITE_LOCK("write_lock"),
    SEMAPHORE_MUTEX_LOCK("semaphore_mutex_lock");
    String lockType;

    LockType(String lockType) {
        this.lockType = lockType;
    }

    public String getLockType() {
        return lockType;
    }
}
