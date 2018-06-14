package com.jeff;

import com.jeff.service.model.User;
import com.jeff.service.service.dao.UserMapper;
import com.jeff.service.service.impl.RedisTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DockerApplicationTests {

	@Autowired
	private RedisTest redisTest;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRedis(){
		redisTest.testRedis();
	}


	@Test
	public void testUserMapper(){
		User user = userMapper.findById(1L); //userDao.selectByPrimaryKey(1L);
		if(user != null){

		}
	}


}
