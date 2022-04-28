package com.yourancc.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/**
 * @author tan.xin
 * @description
 * @date 2022/4/28
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/t1")
    public String test(){
        final HashMap<String,String> map = new HashMap<>();

        final String key1 = UUID.randomUUID().toString();
        final String key2 = UUID.randomUUID().toString();
        final String key3 = UUID.randomUUID().toString();
        final String key4 = UUID.randomUUID().toString();
        map.put(key1,"2");
        map.put(key2,"1");
        map.put(key3,"0");
        map.put(key4,"2");
        //批量设置值
        redisTemplate.opsForValue().multiSet(map);

        //批量取值
        return redisTemplate.opsForValue().multiGet(Arrays.asList(key1,key2,key3,key4)).toString();
    }

    @GetMapping("/t2")
    public String test2(){
        final HashMap<String,String> map = new HashMap<>();

        final String key1 = UUID.randomUUID().toString();
        final String key2 = UUID.randomUUID().toString();
        final String key3 = UUID.randomUUID().toString();
        final String key4 = UUID.randomUUID().toString();
        map.put(key1,"2");
        map.put(key2,"1");
        map.put(key3,"0");
        map.put(key4,"2");
        redisTemplate.opsForSet().add("e1-2",key1,key4);
        redisTemplate.opsForSet().add("e1-0",key3);
        redisTemplate.opsForSet().add("e1-1",key2);

        Boolean member = redisTemplate.opsForSet().isMember("e1-2", key1);
        System.out.println(member);
        redisTemplate.delete("e1-2");
        member = redisTemplate.opsForSet().isMember("e1-2", key1);
        System.out.println(member);
        return redisTemplate.opsForValue().multiGet(Arrays.asList(key1,key2,key3,key4)).toString();
    }
}
