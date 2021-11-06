package com.steak.redissonlock.annotation;

import com.steak.redissonlock.enums.LockType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/11/21:43
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface U2Lock {
    /**
     * 锁类型
     * @return
     */
    LockType lockType() default LockType.REENTRANT_LOCK;
    /**
     * 等待时间
     * @return
     */
    long waitTime() default 25;
    /**
     * 释放锁时间
     * @return
     */
    long leaseTime() default 50;
    /**
     * 时间单位
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;
    /**
     * 锁名称
     * @return
     */
    String lockName() default "";

}
