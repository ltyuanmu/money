package com.lt.money.outbean;

import java.util.Date;

public class AccountMonth {
	/**
	 * 月份
	 */
	private Date month;
	/**
	 * 账务明细
	 */
	private double money;
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
}
