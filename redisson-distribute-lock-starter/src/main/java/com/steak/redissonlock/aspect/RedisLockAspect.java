package com.steak.redissonlock.aspect;

import com.steak.redissonlock.annotation.U2Lock;
import com.steak.redissonlock.enums.LockType;
import com.steak.redissonlock.generator.LockKeyGenerator;
import com.steak.redissonlock.service.RedissonLockService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/12/20:14
 * @Description:
 */
@Aspect
public class RedisLockAspect {

    private final RedissonLockService redissonLockService;

    @Around("@annotation(u2Lock)")
    public Object doLock(ProceedingJoinPoint joinPoint , U2Lock u2Lock) throws Throwable {
        String lockName = u2Lock.lockName();
        if (StringUtils.isBlank(lockName)){
            lockName = LockKeyGenerator.lockKey(joinPoint);
        }
        LockType lockType = u2Lock.lockType();
        long leaseTime = u2Lock.leaseTime();
        long waitTime = u2Lock.waitTime();
        TimeUnit unit = u2Lock.unit();
        redissonLockService.lock(lockName, lockType,leaseTime,waitTime,unit);
        return joinPoint.proceed();
    }

    public RedisLockAspect(RedissonLockService redissonLockService) {
        this.redissonLockService = redissonLockService;
    }


}
