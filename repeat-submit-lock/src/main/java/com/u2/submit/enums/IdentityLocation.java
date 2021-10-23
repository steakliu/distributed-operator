package com.u2.submit.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/14:33
 * @Description:
 */
public enum IdentityLocation {
    HEADER("header"),

    PARAM("param"),

    SESSION("session")
    ;


    IdentityLocation(String identityLocationName) {
        this.identityLocationName = identityLocationName;
    }

    private final String identityLocationName;

    public String getIdentityName() {
        return identityLocationName;
    }
}
