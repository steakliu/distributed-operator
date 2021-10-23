package com.u2.zklock.enums;

/**
 * zookeeper重试策略
 */
public enum RetryPolicyEnum {
    EXPONENTIAL_BACK_OFF_RETRY("exponential-back-off-retry"),
    RETRY_FOREVER("retry-forever"),
    RETRY_N_TIMES("retry-n-times"),
    RETRY_ONE_TIME("retry-one-time"),
    RETRY_UNTIL_ELAPSED("retry-until-elapsed");

    String retryPolicyName;

    RetryPolicyEnum(String retryPolicyName) {
        this.retryPolicyName = retryPolicyName;
    }

    public String getRetryPolicyName() {
        return retryPolicyName;
    }
}
