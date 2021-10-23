package com.u2.submit.service;

import com.u2.submit.annotation.U2RepeatSubmit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/18:04
 * @Description:
 */
public interface RepeatSubmitService {

    void hasKey(String key , U2RepeatSubmit u2RepeatSubmit);

    void submit(String key , U2RepeatSubmit u2RepeatSubmit);
}
