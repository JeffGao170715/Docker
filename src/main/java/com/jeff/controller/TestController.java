package com.jeff.controller;

import com.jeff.service.service.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * Created by Jeff on 2018/5/21.
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

    @Autowired
    private UserMapper userMapper;

    @GetMapping("hello.json")
    public String sayHello(){
        try {
            testAsync(1000);
        }catch (Exception ex){

        }
        return "Hello , Spring Boot!";
    }

    @GetMapping("param/{id}.json")
    public WebAsyncTask testParams(@PathVariable  int id){
        return processRequest(() -> {
//            logger.info("开始 calleable");
//            testAsync(100);    // 休眠0.1秒
//            logger.info("结束等待");
            return String.format("这次访问的id=%d", id);
        });
    }

    @GetMapping("/user/{uid}/find.json")
    public WebAsyncTask findUser(@PathVariable Long uid, Integer cnt){
        return processRequest(() ->{
//            User user = new User();
//            user.setName("小爷");
//            user.setMobile("13000000000");
//            userDao.add(user);
            logger.info("1111111111");
            return userMapper.findById(uid);
        });
    }
}
