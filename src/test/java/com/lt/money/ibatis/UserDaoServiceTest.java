package com.lt.money.ibatis;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lt.money.common.base.BaseTest;
import com.lt.money.common.utils.KeyGenerator;
import com.lt.money.model.User;

public class UserDaoServiceTest extends BaseTest{

	@Autowired
	private UserDaoService userDaoService;
	
	@Test
	public void testUserDaoService(){
		User user = new User();
		user.setId(KeyGenerator.getUuid());
		user.setUsername("lt");
		user.setPassword("lt7jiannan");
		user.setCreateTime(new Date());
		this.userDaoService.insert(user);
	}
}
