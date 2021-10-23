package com.u2.submit.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/14:06
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface U2RepeatSubmit {
    /**
     * 锁接口时间
     * @return
     */
    long lockTime() default 1000L;
    /**
     * 时间单位
     * @return
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
    /**
     * 消息提醒
     * @return
     */
    String msg() default "请勿重复提交";

}
