package com.example.redisdistributelock;

import com.example.redisdistributelock.watch.WatchDog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisDistributeLockApplicationTests {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    static String KEY = "liu-key";

    /**
     * 查看key存在，存在返回1，不存在则设置值和过期日期并返回0
     */
    private static final String RELEASE_LOCK_LUA_SCRIPT =
            "if redis.call('get', KEYS[1]) == false " +
                    "then redis.call('set', KEYS[1], ARGV[1]) " +
                    "redis.call('expire', KEYS[1], ARGV[2]) " +
                    "return 0 else return 1 end";

    private static final String SCRIPT_2 = "if redis";

    String RANDOM = UUID.randomUUID().toString();

    /**
     * 用本地线程保存当前线程的key变量
     */
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 最简单加锁解锁
     */
    @Test
    void simpleLock() {
        //加锁 返回true
        Boolean b = redisTemplate.opsForValue().setIfAbsent(KEY, 1, 10, TimeUnit.SECONDS);
        System.out.println(b);
        //加锁，key已存在，返回false
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(KEY, 1, 10, TimeUnit.SECONDS);
        System.out.println(aBoolean);
        //解锁，删除key
        Boolean delete = redisTemplate.delete(KEY);
        System.out.println("delete "+delete);
    }

    /**
     * 保障加锁原子性，多个操作时原子的，上面的操作时多个命令，如果一个命令成功，一个失败，不具有原子性
     */
    @Test
    void keepAtomic(){
        threadLocal.set(RANDOM);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_LOCK_LUA_SCRIPT,Long.class);
        Long l = redisTemplate.execute(redisScript, Collections.singletonList(KEY),threadLocal.get(),200);
        //System.out.println(l);
        value();
    }

    @Test
    void value(){
        /**
         * 是自己持有的锁再删除，不是自己的锁则不删除
         */
        String script = "if redis.call('GET' , KEYS[1]) == ARGV[1] then return redis.call('DEL' , KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Long execute = redisTemplate.execute(redisScript, Collections.singletonList(KEY), threadLocal.get());
        System.out.println(execute);
    }

    @Test
    void watchDog(){
        redisTemplate.opsForValue().set(KEY,1,3,TimeUnit.SECONDS);
        WatchDog.watchKeys(KEY,5,0,redisTemplate);
    }



}
