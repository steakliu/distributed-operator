package com.steak.redissonlock.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/21/15:19
 * @Description:
 */
public class LockFailException extends RuntimeException{

    private int code = -1;
    private String msg;

    public LockFailException() {
        super();
    }

    public LockFailException(String message) {
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
