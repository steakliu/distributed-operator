package com.u2.zklock.properties.policy;



public class ZkRetryOneTimeProperties {
    int sleepMsBetweenRetry;

    public int getSleepMsBetweenRetry() {
        return sleepMsBetweenRetry;
    }

    public void setSleepMsBetweenRetry(int sleepMsBetweenRetry) {
        this.sleepMsBetweenRetry = sleepMsBetweenRetry;
    }
}
