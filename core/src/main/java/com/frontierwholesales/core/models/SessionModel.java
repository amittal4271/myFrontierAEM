package com.frontierwholesales.core.models;



import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;


public class SessionModel extends WCMUsePojo{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionModel.class);
	 private String authToken;

	    

		@Override
		public void activate() throws Exception {
			LOGGER.debug("activate method...");
			//this.authToken =(String) getRequest().getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
			Cookie cookie = FrontierWholesalesUtils.getCookie(getRequest(), FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
			LOGGER.debug("activate method..."+cookie.getValue().length());
			this.authToken = cookie.getValue();
		}



		public String getAuthToken() {
			return authToken;
		}
		
		
}
