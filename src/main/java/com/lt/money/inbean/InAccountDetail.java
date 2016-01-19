package com.lt.money.inbean;

import java.util.Date;

public class InAccountDetail {
	/**
	 * 用户名
	 */
	private String username;
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
	 * 创建时间
	 */
	private Date createTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
