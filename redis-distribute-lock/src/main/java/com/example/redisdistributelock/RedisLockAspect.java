package com.example.redisdistributelock;

import com.example.redisdistributelock.annotation.RedisLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/20/22:26
 * @Description:
 */
@Aspect
public class RedisLockAspect {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private final RedisTemplate<String,Object> redisTemplate;

    private String key  = UUID.randomUUID().toString();

    @Around("@annotation(redisLock)")
    public Object lock(ProceedingJoinPoint joinPoint , RedisLock redisLock){
        //redisTemplate.opsForHash().
        return  null;
    }

    public RedisLockAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
