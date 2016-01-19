package com.lt.money.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.money.code.BaseCode;
import com.lt.money.common.bean.ResultBean;
@RestController
@RequestMapping("/error")
public class ErrorController {
	/**
	 * 登录
	 * @param inUser
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> error() {
		long start = System.currentTimeMillis();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(
					ResultBean.getSuccess(start,BaseCode.ERROR));
	}
}
