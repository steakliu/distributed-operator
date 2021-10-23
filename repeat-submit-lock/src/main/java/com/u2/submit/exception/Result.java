package com.u2.submit.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/07/19:02
 * @Description:
 */
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result warn(Integer code , String msg){
        return new Result(code,msg);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
