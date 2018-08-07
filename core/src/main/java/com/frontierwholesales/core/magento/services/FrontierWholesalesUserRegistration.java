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
		log.debug("customerRegistration Method Start");
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String customerId = Request.Post(server + "/rest/V1/customers")
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		log.debug("customerRegistration Method End");
		return customerId;
	}
	
	public static String companyRegistration(String pwd,JsonObject params) throws Exception{
		log.debug("companyRegistration Method Start");
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String userDetails = Request.Post(server + "/rest/all/V1/company")
				.addHeader("Authorization",pwd)
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		log.debug("companyRegistration Method End");
		return userDetails;
		
	}
	
	/**
	 * Get list of countries and regions
	 * @param adminToken
	 * @return
	 * @throws Exception
	 */
	public static String getCountriesWithRegions(String adminToken) throws Exception{
		log.debug("getCountriesWithRegions Method Start");
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String countryAndRegions = Request.Get(server + "/rest/all/V1/directory/countries")
				.addHeader("Authorization", adminToken)
               
                .execute().returnContent().asString();
		log.debug("getCountriesWithRegions Method End");
		return countryAndRegions;
	}
	
	/**
	 * reset Password
	 * @param adminToken
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String resetPassword(String adminToken,String jsonData) throws Exception{
		log.debug("resetPassword Method Start");
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String response = Request.Post(server + "/rest/V1/customers/resetPassword")
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		log.debug("resetPassword Method End");
		return response.replace("\"", "");
	}
	
	/**
	 * Update customer email id,first and last name
	 * @param adminToken
	 * @param jsonData
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static String updateCustomers(String adminToken,String jsonData,String id) throws Exception{
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String response = Request.Put(server + "/rest/V1/customers/"+id)
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		
		return response;
	}
	
	/** 
	 * add new address for logged users
	 * @param adminToken
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String addAddress(String adminToken,String jsonData) throws Exception{
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String response = Request.Post(server + "/rest/all/V1/addNewAddress")
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
		
		return response;
	}
	
	/**
	 * Retrieve user details and address list for logged users
	 * @param userToken
	 * @return
	 * @throws Exception
	 */
	public static String getWhoAmI(String userToken) throws Exception{
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		String response = Request.Get(server + "/rest/V1/customers/me")
				.addHeader("Authorization", userToken)
				
                .execute().returnContent().asString();
		
		return response;
	}
	
}
