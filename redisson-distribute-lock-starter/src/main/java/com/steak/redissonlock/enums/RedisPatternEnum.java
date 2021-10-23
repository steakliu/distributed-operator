package com.steak.redissonlock.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/10/15:13
 * @Description:
 */
public enum RedisPatternEnum {

    SINGLE("SINGLE"),

    CLUSTER("CLUSTER"),

    SENTINEL("SENTINEL"),

    REPLICATED("REPLICATED"),

    MASTER_SLAVE("MASTER_SLAVE"),

    RED_LOCK("RED_LOCK");

    private final String pattern;

    RedisPatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }
}
