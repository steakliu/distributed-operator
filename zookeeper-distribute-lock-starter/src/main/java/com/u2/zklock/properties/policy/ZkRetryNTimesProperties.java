package com.u2.zklock.properties.policy;


public class ZkRetryNTimesProperties {
    int n;
    int sleepMsBetweenRetries;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getSleepMsBetweenRetries() {
        return sleepMsBetweenRetries;
    }

    public void setSleepMsBetweenRetries(int sleepMsBetweenRetries) {
        this.sleepMsBetweenRetries = sleepMsBetweenRetries;
    }
}
