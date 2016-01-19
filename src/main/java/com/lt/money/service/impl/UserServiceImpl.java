package com.lt.money.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.money.code.UserCode;
import com.lt.money.common.exception.BusinessException;
import com.lt.money.common.utils.KeyGenerator;
import com.lt.money.common.utils.LtStringUtil;
import com.lt.money.ibatis.UserDaoService;
import com.lt.money.inbean.InUser;
import com.lt.money.inbean.UserToken;
import com.lt.money.model.User;
import com.lt.money.model.UserCriteria;
import com.lt.money.model.UserCriteria.Criteria;
import com.lt.money.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDaoService userDaoService;
	
	@Override
	public String getToken(InUser inUser) throws BusinessException {
		//密码校验
		String username = inUser.getUsername();
		String password = inUser.getPassword();
		password=LtStringUtil.encoderByMd5(password);
		User user = this.getUserByUserName(username);
		if(user==null){
			throw new BusinessException(UserCode.USERERROR);
		}
		if(!user.getPassword().equals(password)){
			throw new BusinessException(UserCode.USERERROR);
		}
		//生成token
		String token =UserToken.getToken(username, user.getId());
		return token;
	}

	@Override
	public String createUser(InUser inUser) throws BusinessException {
		String username = inUser.getUsername();
		String password = inUser.getPassword();
		User user = this.getUserByUserName(username);
		if(user!=null){
			throw new BusinessException(UserCode.USERERROR);
		}
		password=LtStringUtil.encoderByMd5(password);
		user = new User();
		user.setId(KeyGenerator.getUuid());
		user.setPassword(password);
		user.setUsername(username);
		user.setCreateTime(new Date());
		this.userDaoService.insert(user);
		String token =UserToken.getToken(username, user.getId());
		return token;
	}

	@Override
	public void modifyUserPassword(InUser inUser) throws BusinessException {
		//密码校验
		String username = inUser.getUsername();
		String password = inUser.getPassword();
		String oldPassword = inUser.getOldPassword();
		oldPassword=LtStringUtil.encoderByMd5(oldPassword);
		User user = this.getUserByUserName(username);
		if(user==null){
			throw new BusinessException(UserCode.USERERROR);
		}
		if(!user.getPassword().equals(oldPassword)){
			throw new BusinessException(UserCode.USERERROR);
		}
		password=LtStringUtil.encoderByMd5(password);
		user.setPassword(password);
		this.userDaoService.updateByPrimaryKeySelective(user);
	}


	@Override
	public User getUserByUserName(String username) throws BusinessException {
		UserCriteria example = new UserCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = this.userDaoService.selectByExample(example);
		if(list.size()>1){
			throw new BusinessException(UserCode.USERERROR);
		}
		return list.size()==1?list.get(0):null;
	}

}
