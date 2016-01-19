package com.lt.money.common.utils;

import java.util.UUID;

/**
 * 
* @Title: KeyGenerator.java
* @Package com.lt.money.common.utils
* @Description: 业务主键生成工具类
 */
public class KeyGenerator {
	
	/**
	 * 自动生成主键值，uuid 作为主键
	 * @return
	 */
	public static String getUuid(){
		String keyId = UUID.randomUUID().toString().replaceAll("-", "");
		return keyId;
	}
	
	/**
	 * 生成业务主键
	 * @return	String
	 */
	public static String getBusinessCode(){
		return getBusinessCode("");
	}
	
	/**
	 * 生成业务主键
	 * @param	type	业务类型
	 * @return	String
	 */
	public static String getBusinessCode(String type){
		//目前业务主键没有规则，统一生成时间为主键。
		long keyId = System.nanoTime();
		return String.valueOf(keyId);
	}
	
}
