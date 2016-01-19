package com.lt.money.code;

import org.springframework.web.bind.annotation.RequestMethod;


public class BaseSetting {
	public enum OPENURL{
		login("/user/token",RequestMethod.POST),
		createUser("/user/",RequestMethod.POST);
		
		private String url;
		
		private String mothod;

		private OPENURL(String url,RequestMethod mothod) {
			this.url = url;
			this.mothod =mothod.name();
		}

		public String getUrl() {
			return url;
		}

		public String getMethod() {
			return mothod;
		}
	}
}
