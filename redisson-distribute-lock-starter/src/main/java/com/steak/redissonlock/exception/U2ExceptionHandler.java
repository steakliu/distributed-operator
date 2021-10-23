package com.steak.redissonlock.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/21/15:20
 * @Description:
 */
@RestControllerAdvice
public class U2ExceptionHandler {

    @ExceptionHandler(LockFailException.class)
    public R lockFailException(LockFailException lockFailException){
        return new R(lockFailException.getCode(),lockFailException.getMsg());
    }
}
