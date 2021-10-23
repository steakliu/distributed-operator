package com.u2.submit.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/16:53
 * @Description:
 */
public class RepeatSubmitException extends RuntimeException{

    private int code = -1;

    private String msg;

    public RepeatSubmitException(String message) {
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
