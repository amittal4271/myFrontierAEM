package com.frontierwholesales.core.models;



import com.adobe.cq.sightly.WCMUsePojo;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;


public class SessionModel extends WCMUsePojo{

	 private String authToken;

	    

		@Override
		public void activate() throws Exception {
			
			this.authToken =(String) getRequest().getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
			
		}



		public String getAuthToken() {
			return authToken;
		}
		
		
}
