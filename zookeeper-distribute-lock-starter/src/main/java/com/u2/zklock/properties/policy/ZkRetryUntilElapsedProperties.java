package com.u2.zklock.properties.policy;


public class ZkRetryUntilElapsedProperties {
    int maxElapsedTimeMs;
    int sleepMsBetweenRetries;

    public int getMaxElapsedTimeMs() {
        return maxElapsedTimeMs;
    }

    public void setMaxElapsedTimeMs(int maxElapsedTimeMs) {
        this.maxElapsedTimeMs = maxElapsedTimeMs;
    }

    public int getSleepMsBetweenRetries() {
        return sleepMsBetweenRetries;
    }

    public void setSleepMsBetweenRetries(int sleepMsBetweenRetries) {
        this.sleepMsBetweenRetries = sleepMsBetweenRetries;
    }
}
