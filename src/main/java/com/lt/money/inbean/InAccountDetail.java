package com.lt.money.inbean;

import java.util.Date;

public class InAccountDetail {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 添加类型
	 */
	private String type;
	/**
	 * 金钱数
	 */
	private double money;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 添加时间
	 */
	private Date accountDay;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getAccountDay() {
		return accountDay;
	}

	public void setAccountDay(Date accountDay) {
		this.accountDay = accountDay;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
