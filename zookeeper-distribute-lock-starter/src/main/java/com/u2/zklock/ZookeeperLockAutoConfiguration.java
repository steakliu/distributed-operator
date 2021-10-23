package com.u2.zklock;

import com.u2.zklock.aspct.LockAspect;
import com.u2.zklock.enums.RetryPolicyEnum;
import com.u2.zklock.properties.CuratorFrameworkProperties;
import com.u2.zklock.service.LockService;
import com.u2.zklock.service.LockServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@Configuration
@EnableConfigurationProperties(CuratorFrameworkProperties.class)
@Import(LockAspect.class)
@AllArgsConstructor
public class ZookeeperLockAutoConfiguration {

    private CuratorFrameworkProperties curatorFrameworkProperties;

    @Bean("curatorFramework")
    public CuratorFramework curatorFramework() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(curatorFrameworkProperties.getAddress())
                .sessionTimeoutMs(curatorFrameworkProperties.getSessionTimeout())
                .connectionTimeoutMs(curatorFrameworkProperties.getConnectionTimeout())
                .retryPolicy(retryPolicy())
                .build();
        curatorFramework.start();
        return curatorFramework;
    }

    @Bean
    @Scope("prototype")
    public LockService lockService(){
        return new LockServiceImpl(curatorFramework());
    }


    /**
     * zk重视机制
     * @return
     */
    public RetryPolicy retryPolicy(){
        String retryPolicy = curatorFrameworkProperties.getRetryPolicy();
        RetryPolicy policy = null;
        if (Objects.equals(RetryPolicyEnum.EXPONENTIAL_BACK_OFF_RETRY.getRetryPolicyName(),retryPolicy)){
            policy = new ExponentialBackoffRetry(curatorFrameworkProperties.getExponentialBackoffRetry().getBaseSleepTimeMs(), curatorFrameworkProperties.getExponentialBackoffRetry().getMaxRetries(), curatorFrameworkProperties.getExponentialBackoffRetry().getMaxSleepMs());
        }
        if (Objects.equals(RetryPolicyEnum.RETRY_FOREVER.getRetryPolicyName(),retryPolicy)){
            policy = new RetryForever(curatorFrameworkProperties.getRetryForever().getRetryIntervalMs());
        }
        if (Objects.equals(RetryPolicyEnum.RETRY_N_TIMES.getRetryPolicyName(),retryPolicy)){
            policy = new RetryNTimes(curatorFrameworkProperties.getRetryNTimes().getN(),curatorFrameworkProperties.getRetryNTimes().getSleepMsBetweenRetries());
        }
        if (Objects.equals(RetryPolicyEnum.RETRY_ONE_TIME.getRetryPolicyName(),retryPolicy)){
            policy = new RetryOneTime(curatorFrameworkProperties.getRetryOneTime().getSleepMsBetweenRetry());
        }
        if (Objects.equals(RetryPolicyEnum.RETRY_UNTIL_ELAPSED.getRetryPolicyName(),retryPolicy)){
            policy = new RetryUntilElapsed(curatorFrameworkProperties.getRetryUntilElapsed().getMaxElapsedTimeMs(),curatorFrameworkProperties.getRetryUntilElapsed().getSleepMsBetweenRetries());
        }
        return policy;
    }
}
