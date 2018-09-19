package com.frontierwholesales.core.models;



import java.util.Calendar;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class SessionModel extends WCMUsePojo{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionModel.class);
	 private String authToken;
	 // token without bearer to handshake magento commerce
     private String token;
	 private int year;

		@Override
		public void activate() throws Exception {
			LOGGER.debug("activate method Start...");
			
			Calendar now = Calendar.getInstance();
			this.year = now.get(Calendar.YEAR);
			
			Cookie cookie = FrontierWholesalesUtils.getCookie(getRequest(),"CustomerData");
			if(cookie != null) {
				String cookieValue = cookie.getValue();
				this.authToken = FrontierWholesalesUtils.getIdFromObject(cookieValue, "token");
				if(this.authToken != null && this.authToken != "") {
					this.token = this.authToken.substring("Bearer ".length(), this.authToken.length());
				}
			}
			LOGGER.debug("activate method End...");
		}



		public String getAuthToken() {
			return authToken;
		}
		
		public String getToken() {
			return token;
		}
		
		public int getYear() {
			return year;
		}
		
		
}
