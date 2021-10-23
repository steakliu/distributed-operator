package com.steak.redissonlock.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/10/23:59
 * @Description:
 */
public enum LockType {

    FAIR_LOCK,

    MULTI_LOCK,

    READ_LOCK,

    WRITE_LOCK,

    RED_LOCK,

    REENTRANT_LOCK;

    LockType() {
    }
}
