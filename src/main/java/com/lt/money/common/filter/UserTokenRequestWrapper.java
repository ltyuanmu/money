package com.lt.money.common.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

import com.lt.money.inbean.UserToken;

public class UserTokenRequestWrapper extends HttpServletRequestWrapper{

	private Map<String, String[]> params;
	
	public UserTokenRequestWrapper(HttpServletRequest request) {
		super(request);
		params = new HashMap<String, String[]>();
		params.putAll(request.getParameterMap());
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getParameterMap() {  
		return params;  
	}  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration getParameterNames() {  
		Vector l=new Vector(params.keySet());
		return l.elements();  
	}
	
	public String[] getParameterValues(String name) {  
		Object v = params.get(name);  
		if(v==null){  
			return null;  
		}else if(v instanceof String[]){  
			return (String[]) v;  
		}else if(v instanceof String){  
			return new String[]{(String) v};  
		}else{  
			return new String[]{v.toString()};  
		}  
	}  
	public String getParameter(String name) {  
		Object v = params.get(name);  
		if(v==null){  
			return null;  
		}else if(v instanceof String[]){            
			String []strArr=(String[]) v;  
			if(strArr.length>0){  
				return strArr[0];  
			}else{  
				return null;  
			}  
		}else if(v instanceof String){  
			return (String) v;  
		}else{  
			return v.toString();  
		}  
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 */
	public void setParameMap(String key,String value){
		params.put(key,new String[]{value});
	}
	
	
	/**
	 * 通过HTTP请求中的令牌获得登录用户名
	 * @param request
	 * @return
	 */
	public static UserToken getUsernameByServletRequest(ServletRequest request){
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		//获得并校验令牌是否成功令牌
		String token = null;
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					token = cookie.getValue();
					break;
				}
			}
		}
		if(StringUtils.isNotEmpty(token)){
			UserToken userToken = UserToken.getUserToken(token);
			if(userToken!=null){
				return userToken;
			}
		}
		return null;
	}
}
