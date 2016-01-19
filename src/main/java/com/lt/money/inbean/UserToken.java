package com.lt.money.inbean;

import org.apache.commons.lang.StringUtils;

import com.lt.money.common.utils.LtStringUtil;

public class UserToken {
	private static final String split = "_";
	
	private String userId;
	
	private String username;

	private UserToken() {}
	
	public static UserToken getUserToken(String token){
		token=LtStringUtil.decoderByDES(token);
		if(StringUtils.isNotEmpty(token)){
			String[] tokens=token.split(split);
			UserToken uerToken = new UserToken();
			uerToken.setUserId(tokens[1]);
			uerToken.setUsername(tokens[0]);
			return uerToken;
		}
		return null;
	}
	
	
	public static String getToken(String username,String userId){
		return LtStringUtil.encoderByDES(username+split+userId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
