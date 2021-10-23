package com.steak.redissonlock.service;

import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/15/18:23
 * @Description:
 */
public class RedLockCache {

    private static final HashMap<String,List<RedissonClient>> map = new HashMap<>();

    static String key = "redissonClientList";

    public static void keep(List<RedissonClient> list){
        map.put(key,list);
    }

    public static List<RedissonClient> list(){
        return map.get(key);
    }
}
