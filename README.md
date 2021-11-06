## 简介  

u2-lock提供了基于redis,zookeeper的声明式分布式锁，防重提交，在不同的只需要引入相应的starter，进行简单的配置便可使用


### zookeeper分布式锁

锁注解
```
public @interface U2Lock {
    /**
     * 锁名字
     */
    String lockName() default "";
    /**
     * 锁类型
     * @return
     */
    LockType lockType() default LockType.MUTEX_LOCK;
    /**
     * 过期时间
     */
    long requireTime() default 30000;
    /**
     * 时间单位
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
```

yml配置
```
u2-lock:
  address: 127.0.0.1:2181 #多机逗号隔开
  connection-timeout: 1500000000
  session-timeout: 600000
  retry-policy: retry-forever #重试策略
  retry-forever: #对应重试策略配置
    retry-interval-ms: 3000
```

使用
```
    @GetMapping("get")
    @U2Lock(lockName = "order-get",lockType = LockType.MUTEX_LOCK,requireTime = 50000)
    public Map<String,Object> lock(@RequestParam int id , @RequestParam String num){
        Map<String,Object> map = new HashMap<>();
        if (count > 0){
            count--;
            map.put("order_num",count);
            return map;
        }
        map.put("msg","商品已售罄");
        return map;
    }
```

### Redisson分布式锁  
注解  
```
public @interface U2Lock {
    /**
     * 锁类型
     * @return
     */
    LockType lockType() default LockType.REENTRANT_LOCK;
    /**
     * 等待时间
     * @return
     */
    long waitTime() default 25;
    /**
     * 释放锁时间
     * @return
     */
    long leaseTime() default 50;
    /**
     * 时间单位
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;
    /**
     * 锁名称
     * @return
     */
    String lockName() default "";

}
```

yml
```
u2-lock:
  pattern: SINGLE
  password: xiaosi520
  address: redis://101.200.54.149:6379
  idle-connection-timeout: 6000
  idle-size: 10
```  
pattern为redis模式，可单机(SINGLE)，集群(CLUSTER),主从(MASTER_SLAVE)，哨兵(SENTINEL)，使用红锁pattern为RED_LOCK  
红锁配置如下  
```
u2-lock:
pattern: RED_LOCK
password: xiaosi520
address: redis://101.200.54.149:6379
red-lock:
- "redis://101.200.54.149:6379,123456" #123456为密码
- "redis://116.198.160.11:6379,123456"  
- "redis://116.198.160.12:6379,123456"  
- "redis://116.198.160.25:6379,123456"  
- "redis://116.198.160.20:6379,123456"  
```

```
    @GetMapping("test")
    @U2Lock(lockType = LockType.REENTRANT_LOCK,lockName = "red-lock")
    public void test() throws InterruptedException {
        
    }
```

### 防重提交  
基于redis
注解  
```
public @interface U2RepeatSubmit {
    /**
     * 锁接口时间
     * @return
     */
    long lockTime() default 1000L;
    /**
     * 时间单位
     * @return
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
    /**
     * 消息提醒
     * @return
     */
    String msg() default "请勿重复提交";

}
```
yml  
```
spring:
  redis:
    host: 127.0.0.1
    port: 6379
    password: 123456
repeat-submit: #防重提交
  identity: token  #唯一标识
  identity-location: header #参数位置 可为请求头header , 会话session ， 请求参数parameter 
```

使用
```
@U2RepeatSubmit(lockTime = 2000)
    @GetMapping("submit")
    public void submit(@RequestParam("uid") String uid){
        
    }
```
