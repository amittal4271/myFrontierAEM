package com.frontierwholesales.core.models;



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

	    

		@Override
		public void activate() throws Exception {
			LOGGER.debug("activate method...");
			
			Cookie cookie = FrontierWholesalesUtils.getCookie(getRequest(),"CustomerData");
			LOGGER.debug("activate method..."+cookie.getValue());
			
			String cookieValue = cookie.getValue();
			this.authToken = FrontierWholesalesUtils.getIdFromObject(cookieValue, "token");
			
		}



		public String getAuthToken() {
			return authToken;
		}
		
		
}
