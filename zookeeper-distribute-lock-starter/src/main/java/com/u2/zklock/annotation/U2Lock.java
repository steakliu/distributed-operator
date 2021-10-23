package com.u2.zklock.annotation;

import com.u2.zklock.enums.LockType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 锁注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface U2Lock {
    /**
     * 锁名字
     */
    String lockName() default "";
    /**
     * 锁类型-默认为可重入排他锁
     * @return
     */
    LockType lockType() default LockType.MUTEX_LOCK;
    /**
     * 过期时间
     */
    long requireTime() default 30000;
    /**
     * 单位
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
