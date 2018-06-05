package com.frontierwholesales.core.magento.services.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.JsonObject;

@Model(adaptables = SlingHttpServletRequest.class)
public class OrderModel extends BaseModel{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderModel.class);
	private String customerId;
	
	private String customerOrders;
	 @Override
	  protected void init() {
		
		 try {
			 LOGGER.debug("Order Model init method ");
			 String userToken = getUserToken();
		     String customer = connector.getCustomer(userToken);
		     LOGGER.debug("customer response is "+customer);
			JsonObject customerObject = FrontierWholesalesUtils.convertStringToJSONObject(customer);
			
			this.customerId = customerObject.get("id").getAsString();
			LOGGER.debug("customer id "+this.customerId);
			String token = getAdminToken(slingHttpServletRequest);
			
			 this.customerOrders = connector.getCustomerOrders(token, this.customerId);
			
		 }catch(Exception anyEx) {
				anyEx.printStackTrace();
				LOGGER.error("Error in Order Model ",anyEx,anyEx.getMessage());
			}
	 }
	 
	 @PostConstruct
	  protected void activate() {
		 LOGGER.debug("activate method...");
		
	 }
	 
	 public String getCustomerId() {
		 return customerId;
	 }
	
	
	 public String getCustomerOrders() {
		 return customerOrders;
	 }
}
