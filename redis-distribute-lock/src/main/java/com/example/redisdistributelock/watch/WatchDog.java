package com.example.redisdistributelock.watch;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/12/16:19
 * @Description:
 */
public class WatchDog {

    private static final ScheduledExecutorService service = Executors.newScheduledThreadPool(100);


    /**
     * @param key 要监视的key
     * @param period 多长时间监视一次
     * @param initialDelay 启动时多久开始监听
     */
    public static void watchKeys(String key,Integer period,Integer initialDelay,RedisTemplate<String,Object> redisTemplate){
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("--------------");
                /**
                 * 获取key过期时间
                 */
                Object o = redisTemplate.opsForValue().get(key);
                if (o != null){
                    Long expire = redisTemplate.getExpire(key);
                    if (expire != null && expire < 5){
                        redisTemplate.expire(key,20,TimeUnit.SECONDS);
                    }
                }

            }
        },0,3, TimeUnit.SECONDS);
    }

}
