package com.frontierwholesales.core.magento.services;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class FrontierWholesalesUserRegistration {

	 private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesUserRegistration.class);
	
	 /**
	  * customer registration
	  * @param params
	  * @return
	  * @throws Exception
	  */
	public static String customerRegistration(JsonObject params) throws Exception{
		
		
		
		
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String customerId = Request.Post(server + "/rest/V1/customers")
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		log.info("customer id is "+customerId);
		return customerId;
	}
	
	public static String companyRegistration(String pwd,JsonObject params) throws Exception{
		try {
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String userDetails = Request.Post(server + "/rest/all/V1/company")
				.addHeader("Authorization",pwd)
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		log.info("customer id is "+userDetails);
		return userDetails;
		}catch(Exception anyEx) {
			log.error("Exception in during the company registration :"+anyEx.getMessage());
			anyEx.printStackTrace();
		}
		return null;
	}
	
	public static String getCountriesWithRegions(String adminToken) throws Exception{
		
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String countryAndRegions = Request.Get(server + "/rest/all/V1/directory/countries")
				.addHeader("Authorization", adminToken)
               
                .execute().returnContent().asString();
		
		return countryAndRegions;
	}
	
	public static String resetPassword(String adminToken,String jsonData) throws Exception{

		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String response = Request.Get(server + "/rest/V1/customers/resetPassword")
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		
		return response;
	}
	
	public static String updateCustomers(String adminToken,String jsonData,String id) throws Exception{
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String response = Request.Put(server + "/rest/V1/customers/"+id)
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		
		return response;
	}
	
	public static String addAddress(String adminToken,String jsonData) throws Exception{
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String response = Request.Put(server + "/rest/all/V1/addNewAddress/")
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		
		return response;
	}
	
}
