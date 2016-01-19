package com.lt.money.service;

import com.lt.money.common.exception.BusinessException;
import com.lt.money.inbean.InUser;
import com.lt.money.model.User;

public interface UserService {
	
	/**
	 * 通过用户名和密码获取登录token
	 * @param inUser
	 * @return
	 */
	public String getToken(InUser inUser) throws BusinessException;
	/**
	 * 通过登录名和密码创建用户名 并获得token
	 * @param inUser
	 */
	public String createUser(InUser inUser) throws BusinessException;
	/**
	 * 修改用户名密码
	 * @param inUser
	 */
	public void modifyUserPassword(InUser inUser) throws BusinessException;
	/**
	 * 通过登录名获得用户信息
	 * @param username
	 * @return
	 * @throws BusinessException
	 */
	public User getUserByUserName(String username) throws BusinessException;
}
