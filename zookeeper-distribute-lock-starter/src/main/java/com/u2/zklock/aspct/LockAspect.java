package com.u2.zklock.aspct;

import com.u2.zklock.annotation.U2Lock;
import com.u2.zklock.enums.LockType;
import com.u2.zklock.generator.LockKeyGenerator;
import com.u2.zklock.service.LockService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.util.concurrent.TimeUnit;

/**
 * 锁切面
 */
@Aspect
public class LockAspect implements ApplicationContextAware {

    ApplicationContext applicationContext;

    final LockService lockService;
    /**
     * 加锁操作
     *
     * @param joinPoint
     * @param u2Lock
     * @return
     */
    @Around("@annotation(u2Lock)")
    public Object doZkLock(ProceedingJoinPoint joinPoint, U2Lock u2Lock) throws Throwable {

        LockType lockType = u2Lock.lockType();
        long requireTime = u2Lock.requireTime();
        TimeUnit unit = u2Lock.unit();
        /**
         * 锁名称(未指定锁名称则用方法作为参数)
         */
        String lockName = u2Lock.lockName();
        if (StringUtils.isBlank(lockName)) {
            lockName = LockKeyGenerator.lockKey(joinPoint);
        }
        lockService.lock(requireTime,unit,lockName,lockType);
        return joinPoint.proceed();
    }

    /**
     * 释放锁
     *
     * @param u2Lock
     */
    @AfterReturning("@annotation(u2Lock)")
    public void doUnLock(U2Lock u2Lock) {
        lockService.unlock();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public LockAspect(LockService lockService) {
        this.lockService = lockService;
    }
}
