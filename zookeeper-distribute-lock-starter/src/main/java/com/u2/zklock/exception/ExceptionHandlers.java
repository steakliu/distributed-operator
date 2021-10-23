package com.u2.zklock.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/07/19:08
 * @Description:
 */
@RestControllerAdvice
public class ExceptionHandlers {


    @ExceptionHandler({LockException.class})
    public LockResult lockException(LockException lockException){
        return new LockResult(lockException.getCode(),lockException.getMsg());
    }
}
