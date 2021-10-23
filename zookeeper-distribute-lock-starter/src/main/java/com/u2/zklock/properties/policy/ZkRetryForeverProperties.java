package com.u2.zklock.properties.policy;



public class ZkRetryForeverProperties {
    int retryIntervalMs = 2000;

    public int getRetryIntervalMs() {
        return retryIntervalMs;
    }

    public void setRetryIntervalMs(int retryIntervalMs) {
        this.retryIntervalMs = retryIntervalMs;
    }
}
