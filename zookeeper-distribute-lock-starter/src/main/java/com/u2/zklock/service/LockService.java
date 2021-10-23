package com.u2.zklock.service;

import com.u2.zklock.enums.LockType;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/15/11:14
 * @Description:
 */
public interface LockService {
      void lock(long requireTime, TimeUnit unit ,String lockName , LockType lockType);

      void unlock();
}
