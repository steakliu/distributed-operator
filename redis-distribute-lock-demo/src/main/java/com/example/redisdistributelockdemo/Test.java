package com.example.redisdistributelockdemo;

import com.baomidou.lock.annotation.Lock4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/12/23:27
 * @Description:
 */
@RestController
public class Test {

    static int count = 500;

    @GetMapping("lock4j")
    @Lock4j(expire = 50000 , acquireTimeout = 20000)
    public int lock4j(){
        return count--;
    }
}
