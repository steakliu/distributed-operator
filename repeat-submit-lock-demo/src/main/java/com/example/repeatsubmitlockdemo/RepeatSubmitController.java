package com.example.repeatsubmitlockdemo;

import com.u2.submit.annotation.U2RepeatSubmit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/15:21
 * @Description:
 */
@RestController
public class RepeatSubmitController {

    @U2RepeatSubmit(lockTime = 2000)
    @GetMapping("submit")
    public String submit(@RequestParam("uid") String uid){
        return "防重提交测试";
    }
}
