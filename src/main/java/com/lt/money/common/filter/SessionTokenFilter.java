package com.lt.money.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


import com.lt.money.code.BaseSetting.OPENURL;
import com.lt.money.inbean.UserToken;


public class SessionTokenFilter implements Filter{

	//private final static Logger logger = LoggerFactory.getLogger(SessionTokenFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//获得调用路径 和调用方法 
		String url = httpRequest.getServletPath();
		String method = httpRequest.getMethod();
		
		// 过滤不需要token 的方法
		for (OPENURL openUrl : OPENURL.values()) {
			if (openUrl.getUrl().equals(url)
					&& openUrl.getMethod().equals(method)) {
				chain.doFilter(httpRequest, response);
				return;
			}
		}
		//校验是否获得登录名
		UserToken userToken = UserTokenRequestWrapper.getUsernameByServletRequest(request);
		if (userToken!=null) {
			UserTokenRequestWrapper requestWarpper = new UserTokenRequestWrapper(httpRequest);
			requestWarpper.setParameMap("username", userToken.getUsername());
			requestWarpper.setParameMap("userId", userToken.getUserId());
			chain.doFilter(requestWarpper, response);
		} else {
			httpRequest.getRequestDispatcher("/error/").forward(request, response);
		}
	}

	@Override
	public void destroy() {
	}
	
}
