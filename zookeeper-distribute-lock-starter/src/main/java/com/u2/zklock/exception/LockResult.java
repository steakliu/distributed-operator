package com.u2.zklock.exception;

import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/07/19:02
 * @Description:
 */
public class LockResult {
    private Integer code;
    private String message;

    public LockResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
