package com.u2.zklock.properties;

import com.u2.zklock.constants.ZkConstant;
import com.u2.zklock.enums.RetryPolicyEnum;
import com.u2.zklock.properties.policy.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "u2-lock")
public class CuratorFrameworkProperties {


    String address = "127.0.0.1:2181";

    int connectionTimeout = 150000;

    int sessionTimeout = 600000;

    String retryPolicy = RetryPolicyEnum.EXPONENTIAL_BACK_OFF_RETRY.getRetryPolicyName();

    @NestedConfigurationProperty
    ZkExponentialBackoffRetryProperties exponentialBackoffRetry;

    @NestedConfigurationProperty
    ZkRetryForeverProperties retryForever;

    @NestedConfigurationProperty
    ZkRetryNTimesProperties retryNTimes;

    @NestedConfigurationProperty
    ZkRetryOneTimeProperties retryOneTime;

    @NestedConfigurationProperty
    ZkRetryUntilElapsedProperties retryUntilElapsed;

    public CuratorFrameworkProperties() {
        this.exponentialBackoffRetry = new ZkExponentialBackoffRetryProperties();
        this.retryForever = new ZkRetryForeverProperties();
        this.retryNTimes = new ZkRetryNTimesProperties();
        this.retryOneTime = new ZkRetryOneTimeProperties();
        this.retryUntilElapsed = new ZkRetryUntilElapsedProperties();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(String retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public ZkExponentialBackoffRetryProperties getExponentialBackoffRetry() {
        return exponentialBackoffRetry;
    }

    public void setExponentialBackoffRetry(ZkExponentialBackoffRetryProperties exponentialBackoffRetry) {
        this.exponentialBackoffRetry = exponentialBackoffRetry;
    }

    public ZkRetryForeverProperties getRetryForever() {
        return retryForever;
    }

    public void setRetryForever(ZkRetryForeverProperties retryForever) {
        this.retryForever = retryForever;
    }

    public ZkRetryNTimesProperties getRetryNTimes() {
        return retryNTimes;
    }

    public void setRetryNTimes(ZkRetryNTimesProperties retryNTimes) {
        this.retryNTimes = retryNTimes;
    }

    public ZkRetryOneTimeProperties getRetryOneTime() {
        return retryOneTime;
    }

    public void setRetryOneTime(ZkRetryOneTimeProperties retryOneTime) {
        this.retryOneTime = retryOneTime;
    }

    public ZkRetryUntilElapsedProperties getRetryUntilElapsed() {
        return retryUntilElapsed;
    }

    public void setRetryUntilElapsed(ZkRetryUntilElapsedProperties retryUntilElapsed) {
        this.retryUntilElapsed = retryUntilElapsed;
    }

}
