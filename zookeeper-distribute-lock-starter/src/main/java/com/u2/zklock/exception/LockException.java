package com.u2.zklock.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/06/20:59
 * @Description:
 */
public class LockException extends RuntimeException{

    private int code = -1;
    private String msg;

    public LockException() {
        super();
    }

    public LockException(String message) {
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
