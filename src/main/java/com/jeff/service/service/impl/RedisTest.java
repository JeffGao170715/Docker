package com.jeff.service.service.impl;

import com.jeff.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jeff on 2018/6/8.
 */
@Service
public class RedisTest {
    Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public void testRedis(){
        User user = new User();
        user.setMobile("15900000000");
        user.setName("测试");
        user.setId(101L);
        logger.info("<======redis start======>");
        redisTemplate.opsForValue().set("test",user);
        redisTemplate.expire("test", 100, TimeUnit.SECONDS);
        logger.info("<======redis end======>");

        User u2 = (User) redisTemplate.opsForValue().get("test");
        if(u2 != null){
            redisTemplate.opsForValue().set("test_append", "first");
            Integer tt = redisTemplate.opsForValue().append("test_append", "second");
            if(tt != null){

            }
        }
    }

}
