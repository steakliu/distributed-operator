package com.steak.redissonlock;

import com.steak.redissonlock.aspect.RedisLockAspect;
import com.steak.redissonlock.enums.RedisPatternEnum;
import com.steak.redissonlock.properties.RedissonProperties;
import com.steak.redissonlock.service.RedLockCache;
import com.steak.redissonlock.service.RedissonLockService;
import com.steak.redissonlock.service.RedissonLockServiceImpl;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/10/15:04
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
@ConditionalOnClass(RedissonLockService.class)
@Import(RedisLockAspect.class)
public class RedissonLockAutoConfiguration {

    final RedissonProperties redissonProperties;

    @Bean
    public RedissonClient redissonClient() {
        RedissonClient redissonClient = null;
        if (RedisPatternEnum.SINGLE.getPattern().equals(redissonProperties.getPattern())){
            redissonClient = single();
        }
        if (RedisPatternEnum.CLUSTER.getPattern().equals(redissonProperties.getPattern())){
            redissonClient = cluster();
        }
        if (RedisPatternEnum.SENTINEL.getPattern().equals(redissonProperties.getPattern())){
            redissonClient = sentinel();
        }
        if (RedisPatternEnum.REPLICATED.getPattern().equals(redissonProperties.getPattern())){
            redissonClient = replicated();
        }
        if (RedisPatternEnum.MASTER_SLAVE.getPattern().equals(redissonProperties.getPattern())){
            redissonClient = masterSlave();
        }
        if (RedisPatternEnum.RED_LOCK.getPattern().equals(redissonProperties.getPattern())){
            redLock();

        }
        return redissonClient;
    }

    @Bean
    public RedissonLockService redissonLockService(){
        return new RedissonLockServiceImpl(redissonClient());
    }

    /**
     * 单机
     * @return
     */
    public RedissonClient single() {
        Config config = new Config();
        SingleServerConfig single = config.useSingleServer();
        single
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setTimeout(redissonProperties.getTimeout())
                .setPassword(redissonProperties.getPassword())
                .setConnectTimeout(redissonProperties.getConnectionTimeout())
                .setDatabase(redissonProperties.getDatabase())
                .setAddress(redissonProperties.getAddress())
                .setKeepAlive(redissonProperties.getKeepAlive())
                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                .setConnectionMinimumIdleSize(redissonProperties.getIdleSize())
                .setConnectTimeout(redissonProperties.getConnectionTimeout());
        return Redisson.create(config);
    }

    /**
     * 集群
     * @return
     */
    public RedissonClient cluster() {
        Config config = new Config();
        ClusterServersConfig cluster = config.useClusterServers();
        cluster.addNodeAddress(redissonProperties.getNodeAddress())
                .
                setMasterConnectionPoolSize(redissonProperties.getConnectionPoolSize()).
                setMasterConnectionMinimumIdleSize(redissonProperties.getIdleSize()).
                setSlaveConnectionMinimumIdleSize(redissonProperties.getIdleSize()).
                setSlaveConnectionPoolSize(redissonProperties.getConnectionPoolSize()).
                addNodeAddress(redissonProperties.getNodeAddress()).
                setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout()).
                setTimeout(redissonProperties.getTimeout()).
                setPassword(redissonProperties.getPassword()).
                setConnectTimeout(redissonProperties.getConnectionTimeout()).
                setKeepAlive(redissonProperties.getKeepAlive());
        return Redisson.create(config);
    }

    /**
     * 主从模式
     * @return
     */
    public RedissonClient masterSlave(){
        Config config = new Config();
        MasterSlaveServersConfig masterSlaveServers = config.useMasterSlaveServers();
        masterSlaveServers.setMasterAddress(redissonProperties.getMasterAddress())
                .addSlaveAddress(redissonProperties.getSlaveAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setPassword(redissonProperties.getPassword())
                .setConnectTimeout(redissonProperties.getConnectionTimeout())
                .setDatabase(redissonProperties.getDatabase())
                .setKeepAlive(redissonProperties.getKeepAlive())
                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                .setConnectTimeout(redissonProperties.getConnectionTimeout())
                .setMasterAddress(redissonProperties.getMasterAddress())
                .addSlaveAddress(redissonProperties.getSlaveAddress())
                .setMasterConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setMasterConnectionMinimumIdleSize(redissonProperties.getIdleSize())
                .setSlaveConnectionMinimumIdleSize(redissonProperties.getIdleSize())
                .setSlaveConnectionPoolSize(redissonProperties.getConnectionPoolSize());
        return Redisson.create(config);
    }

    /**
     * 哨兵模式
     * @return
     */
    public RedissonClient sentinel(){
        Config config = new Config();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
        sentinelServersConfig.setMasterName(redissonProperties.getMasterName())
                .addSentinelAddress(redissonProperties.getSentinelAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setPassword(redissonProperties.getPassword())
                .setConnectTimeout(redissonProperties.getConnectionTimeout())
                .setDatabase(redissonProperties.getDatabase())
                .setKeepAlive(redissonProperties.getKeepAlive())
                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                .setConnectTimeout(redissonProperties.getConnectionTimeout())
                .setMasterConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setMasterConnectionMinimumIdleSize(redissonProperties.getIdleSize())
                .setSlaveConnectionMinimumIdleSize(redissonProperties.getIdleSize())
                .setSlaveConnectionPoolSize(redissonProperties.getConnectionPoolSize());
        return Redisson.create(config);
    }

    /**
     * 云托管模式
     * @return
     */
    public RedissonClient replicated(){
        Config config = new Config();
        ReplicatedServersConfig replicatedServers = config.useReplicatedServers();
        replicatedServers.addNodeAddress(redissonProperties.getNodeAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setPassword(redissonProperties.getPassword())
                .setConnectTimeout(redissonProperties.getConnectionTimeout())
                .setDatabase(redissonProperties.getDatabase())
                .setKeepAlive(redissonProperties.getKeepAlive())
                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                .setConnectTimeout(redissonProperties.getConnectionTimeout())
                .setMasterConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setMasterConnectionMinimumIdleSize(redissonProperties.getIdleSize())
                .setSlaveConnectionMinimumIdleSize(redissonProperties.getIdleSize())
                .setSlaveConnectionPoolSize(redissonProperties.getConnectionPoolSize());
        return Redisson.create(config);
    }

    /**
     * 使用红锁
     */
    public void redLock(){
        single();
        String[] redLockArray = redissonProperties.getRedLock();
        List<RedissonClient> redLockList = new ArrayList<>();
        for (String s : redLockArray) {
            String[] str = s.split(",");
            Config config = new Config();
            config.useSingleServer().setAddress(str[0]).setPassword(str[1]);
            redLockList.add(Redisson.create(config));
        }
        RedLockCache.keep(redLockList);
    }

    public RedissonLockAutoConfiguration(RedissonProperties redissonProperties) {
        this.redissonProperties = redissonProperties;
    }
}
