package com.example.zookeeperdistributelockdemo;

import com.u2.zklock.annotation.U2Lock;
import com.u2.zklock.enums.LockType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
public class controller {

    static int count = 50;

    @GetMapping("get")
    @U2Lock(lockName = "order-get",lockType = LockType.MUTEX_LOCK,requireTime = 50000)
    public Map<String,Object> lock(@RequestParam int id , @RequestParam String num){
        Map<String,Object> map = new HashMap<>();
        if (count > 0){
            count--;
            map.put("order_num",count);
            return map;
        }
        map.put("msg","商品已售罄");
        return map;
    }
}
