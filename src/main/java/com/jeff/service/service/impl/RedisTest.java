package com.jeff.service.service.impl;

import com.jeff.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Jeff on 2018/6/8.
 */
@Service
public class RedisTest {
    Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void testRedis(){
        User user = new User();
        user.setMobile("15900000000");
        user.setName("测试");
        user.setId(101L);
        logger.info("<======redis start======>");
        redisTemplate.opsForValue().set("test","this is a test!");
        logger.info("<======redis end======>");
    }

}
