package com.frontierwholesales.core.magento.services.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class})
public class PaymentModel extends BaseModel{
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentModel.class);
	
	private String shippingInfoResponse;
	
	private String emailId;
	
	 @PostConstruct
	  protected void init() {
		 mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		 try {
			 LOGGER.debug("Inside Init method of PaymentModel");
		String address =(String) slingHttpServletRequest.getSession().getAttribute("ShippingInfo");
		this.emailId = getBillingEmailId(address);
		String userToken = getUserToken();
		LOGGER.debug("address is "+address);
		
		String addressJson = parseJsonObject(address);
		LOGGER.debug("address json is "+addressJson);
		this.shippingInfoResponse= connector.getShippingInfo(userToken, addressJson);
		
		LOGGER.debug("Response is "+this.shippingInfoResponse);
		 }catch(Exception anyEx) {
			LOGGER.error("Error in paymentmodel ",anyEx,anyEx.getMessage());
		}
	 }
	 
	 private String parseJsonObject(String object) throws Exception{
		 Gson json = new Gson();
		
		 JsonElement element = json.fromJson(object,JsonElement.class);
		
		 JsonObject addressObj = element.getAsJsonObject();
		
		 return addressObj.toString();
	 }
	 
	 private String getBillingEmailId(String response) throws Exception{
		 
		 Gson json = new Gson();
		
		 JsonElement element = json.fromJson(response,JsonElement.class);
		
		 JsonObject object = element.getAsJsonObject();
		JsonElement addressInfoElement =  object.get("addressInformation");
		JsonObject billingAddress = addressInfoElement.getAsJsonObject();
		JsonElement billingElement = billingAddress.get("billing_address");
		JsonObject emailObject = billingElement.getAsJsonObject();
		String email = emailObject.get("email").getAsString();
		 return email;
	 }
	 
	 public String getShippingInfoResponse() {
		 return shippingInfoResponse;
	 }
	 
	 public String getEmailId() {
		 return emailId;
	 }
	
}
