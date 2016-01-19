package com.lt.money.common.bean;


public class ResultBean {
	/**
	 * 方法执行毫秒数
	 */
	private long millis;
	
	/**
	 * 反参
	 */
	private Object entity;
	
	
	private ResultBean(long millis, Object entity) {
		super();
		this.millis = System.currentTimeMillis()-millis;
		this.entity = entity;
	}
	public static ResultBean getSuccess(long millis, Object entity) {
		return new ResultBean(millis,entity);
	}
	
	public static ResultBean getError(long millis, String code) {
		return new ResultBean(millis,code);
	}
	
	public long getMillis() {
		return millis;
	}
	public void setMillis(long millis) {
		this.millis = millis;
	}
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
}
