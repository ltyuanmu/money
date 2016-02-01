package com.lt.money.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
	/**
	 * 将yyyy-MM-dd HH:mm:ss 转换 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static Date formatForDay(Date date){
		if(date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss 转换 yyyy-MM
	 * @param date
	 * @return
	 */
	public static Date formatForMonth(Date date){
		if(date == null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 时间比较  参数1 和参数2相比较 格式不正确返回空
	 * @param date 
	 * @param comDate
	 * @return
	 */
	public static Integer compareDate(Date date,Date comDate)
	{
		try {
			Calendar c_1 = Calendar.getInstance();
			Calendar c_2 = Calendar.getInstance();
			
			c_1.setTime(date);
			c_2.setTime(comDate);
			
			return c_1.compareTo(c_2);
		} catch (Exception e) {
			return null;
		}
	}
	
}
