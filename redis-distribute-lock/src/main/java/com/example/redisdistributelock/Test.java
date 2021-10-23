package com.example.redisdistributelock;

import com.example.redisdistributelock.watch.WatchDog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/11/23:26
 * @Description:
 */
@RestController
public class Test {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    String KEY = "liu-key";


    private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


    @GetMapping("redis")
    public void redis(){
        Boolean b = redisTemplate.opsForValue().setIfAbsent(KEY, 1, 3, TimeUnit.SECONDS);
        WatchDog.watchKeys(KEY,3,0,redisTemplate);
    }

    @GetMapping("redis2")
    public void redis2(){
        Boolean b = redisTemplate.opsForValue().setIfAbsent(KEY + "123", 1, 3, TimeUnit.SECONDS);
        WatchDog.watchKeys(KEY + "123",3,0,redisTemplate);
    }
}
