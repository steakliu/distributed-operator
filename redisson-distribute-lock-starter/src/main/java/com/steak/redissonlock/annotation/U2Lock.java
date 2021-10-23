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

    LockType lockType() default LockType.REENTRANT_LOCK;

    long waitTime() default 25;

    long leaseTime() default 50;

    TimeUnit unit() default TimeUnit.SECONDS;

    String lockName() default "";

}
