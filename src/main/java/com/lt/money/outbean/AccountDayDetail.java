package com.lt.money.outbean;

import java.util.Date;

public class AccountDayDetail {
	private String accountDetailId;
	
	private Date day;
	
	private String type;
	
	private String desc;

	public String getAccountDetailId() {
		return accountDetailId;
	}

	public void setAccountDetailId(String accountDetailId) {
		this.accountDetailId = accountDetailId;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
