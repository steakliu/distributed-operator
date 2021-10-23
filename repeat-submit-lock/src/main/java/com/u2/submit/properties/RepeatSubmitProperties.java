package com.u2.submit.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/14:24
 * @Description:
 */
@ConfigurationProperties(prefix = "repeat-submit")
public class RepeatSubmitProperties {
    /**
     * 身份标识（如token,userId）
     */
    private String identity;
    /**
     * 参数位置(如请求头，请求参数，session等)
     */
    private String identityLocation;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIdentityLocation() {
        return identityLocation;
    }

    public void setIdentityLocation(String identityLocation) {
        this.identityLocation = identityLocation;
    }
}
