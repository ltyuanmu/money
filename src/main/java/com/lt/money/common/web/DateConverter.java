package com.lt.money.common.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {    
	@Override    
	public Date convert(String source) { 
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");    
	   dateFormat.setLenient(false);    
	   try {    
	       return dateFormat.parse(source);
	   } catch (ParseException e) {    
	       e.printStackTrace();    
	   }           
	   return null;    
	}  
}