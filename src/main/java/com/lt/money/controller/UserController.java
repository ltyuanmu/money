package com.lt.money.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.lt.money.code.BaseCode;
import com.lt.money.common.bean.ResultBean;
import com.lt.money.common.exception.BusinessException;
import com.lt.money.inbean.InUser;
import com.lt.money.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 * @param inUser
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/token", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> login(InUser inUser,HttpServletResponse response) {
		long start = System.currentTimeMillis();
		try {
			//校验
			checkLogin(inUser);
			
			String token=this.userService.getToken(inUser);
			Cookie cookieToken = new Cookie("token",token);
			cookieToken.setMaxAge(60 * 60 * 24 * 365);
			cookieToken.setMaxAge(60 * 60 * 24);
			cookieToken.setHttpOnly(false);
			cookieToken.setPath("/");
			response.addCookie(cookieToken);
			return ResponseEntity.ok(ResultBean.getSuccess(start, token));
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					ResultBean.getSuccess(start, e.getMessage()));
		}
	}
	
	private void checkLogin(InUser inUser) throws BusinessException{
		if(StringUtils.isEmpty(inUser.getUsername())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(StringUtils.isEmpty(inUser.getPassword())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
	}
	
	/**
	 * 创建用户
	 * @param inUser
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> createUser(InUser inUser,HttpServletResponse response) {
		long start = System.currentTimeMillis();
		try {
			//校验
			checkCreateuser(inUser);
			
			String token=this.userService.createUser(inUser);
			Cookie cookieToken = new Cookie("token",token);
			cookieToken.setMaxAge(60 * 60 * 24 * 365);
			cookieToken.setMaxAge(60 * 60 * 24);
			cookieToken.setHttpOnly(false);
			cookieToken.setPath("/");
			response.addCookie(cookieToken);
			return ResponseEntity.ok(ResultBean.getSuccess(start, token));
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					ResultBean.getSuccess(start, e.getMessage()));
		}
	}
	
	private void checkCreateuser(InUser inUser) throws BusinessException{
		if(StringUtils.isEmpty(inUser.getUsername())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(StringUtils.isEmpty(inUser.getPassword())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
	}
	
	/**
	 * 修改密码
	 * @param inUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/password", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> modifyUserPassword(InUser inUser) {
		long start = System.currentTimeMillis();
		try {
			//校验
			checkModifyUserPassword(inUser);
			this.userService.modifyUserPassword(inUser);
			return ResponseEntity.ok(ResultBean.getSuccess(start, BaseCode.SUCCESS));
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					ResultBean.getSuccess(start, e.getMessage()));
		}
	}
	
	private void checkModifyUserPassword(InUser inUser) throws BusinessException{
		if(StringUtils.isEmpty(inUser.getUsername())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(StringUtils.isEmpty(inUser.getPassword())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
		if(StringUtils.isEmpty(inUser.getOldPassword())){
			throw new BusinessException(BaseCode.PARAMERROE);
		}
	}
	
}
