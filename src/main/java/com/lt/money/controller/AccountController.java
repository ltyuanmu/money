package com.lt.money.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lt.money.common.bean.ResultBean;


@RestController
@RequestMapping("/account")
public class AccountController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/test", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<ResultBean> projectPageInit(Date date) {
		Long start = Calendar.getInstance().getTimeInMillis();
	//	return ResponseEntity.ok().body(new ResultBean(start ,"你好啊!!!!"));
		return ResponseEntity.ok(ResultBean.getSuccess(start ,"你好啊!!!!"+date));
	}

}
