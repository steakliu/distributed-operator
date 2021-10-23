package com.u2.submit.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/16:54
 * @Description:
 */
@RestControllerAdvice
public class SteakExceptionHandler {

    @ExceptionHandler({RepeatSubmitException.class})
    public Result submitException(RepeatSubmitException repeatSubmitException){
        return Result.warn(repeatSubmitException.getCode() , repeatSubmitException.getMsg());
    }
}
