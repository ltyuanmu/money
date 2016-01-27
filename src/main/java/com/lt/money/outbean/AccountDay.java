package com.lt.money.outbean;

import java.util.Date;

public class AccountDay {
	private String accountId;
	/**
	 * 天数
	 */
	private Date day;
	/**
	 * 账务明细
	 */
	private double money;
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
