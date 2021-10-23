package com.u2.zklock.properties.policy;


public class ZkExponentialBackoffRetryProperties {
    int baseSleepTimeMs = 2000;

    int maxRetries = 20;

    int maxSleepMs = 3000;

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getMaxSleepMs() {
        return maxSleepMs;
    }

    public void setMaxSleepMs(int maxSleepMs) {
        this.maxSleepMs = maxSleepMs;
    }
}
