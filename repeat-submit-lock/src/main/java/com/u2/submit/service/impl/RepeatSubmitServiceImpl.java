package com.u2.submit.service.impl;

import com.u2.submit.annotation.U2RepeatSubmit;
import com.u2.submit.exception.RepeatSubmitException;
import com.u2.submit.service.RepeatSubmitService;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/17:52
 * @Description:
 */
public class RepeatSubmitServiceImpl implements RepeatSubmitService {

    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void hasKey(String key , U2RepeatSubmit u2RepeatSubmit){
        Boolean hasKey = redisTemplate.hasKey(key);
        if (Boolean.TRUE.equals(hasKey)){
            throw new RepeatSubmitException(u2RepeatSubmit.msg());
        }
    }

    @Override
    public void submit(String key , U2RepeatSubmit u2RepeatSubmit){
        redisTemplate.opsForValue().set(key,key, u2RepeatSubmit.lockTime(), u2RepeatSubmit.unit());
    }

    public RepeatSubmitServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
