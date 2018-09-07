package com.frontierwholesales.core.magento.services;

import java.io.InputStream;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
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
		InputStream inputStream = Request.Post(server + "/rest/V1/customers")
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnResponse().getEntity().getContent();
		 String customerId = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
		log.debug("customerRegistration Method End");
		return customerId;
	}
	
	public static String companyRegistration(String pwd,JsonObject params) throws Exception{
		log.debug("companyRegistration Method Start");
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		InputStream inputStream = Request.Post(server + "/rest/all/V1/company")
				.addHeader("Authorization",pwd)
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnResponse().getEntity().getContent();
		 String userDetails = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
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
		InputStream inputStream = Request.Get(server + "/rest/all/V1/directory/countries")
				.addHeader("Authorization", adminToken)
               
				.execute().returnResponse().getEntity().getContent();
		 String countryAndRegions = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
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
		InputStream inputStream =  Request.Post(server + "/rest/V1/customers/resetPassword")
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
				.execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
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
		log.debug("updateCustomers Method Start");
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		InputStream inputStream =  Request.Put(server + "/rest/V1/customers/"+id)
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
				 .execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
		 log.debug("updateCustomers Method End");
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
		log.debug("addAddress Method Start");
		String server = FrontierWholesalesMagentoCommerceConnector.getServer();
		InputStream inputStream =Request.Post(server + "/rest/all/V1/addNewAddress")
				.addHeader("Authorization", adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
				 .execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
		 log.debug("addAddress Method End");
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
		InputStream inputStream = Request.Get(server + "/rest/V1/customers/me")
				.addHeader("Authorization", userToken)
				
				.execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
		return response;
	}
	
	public static String validateToken(String adminToken,String resetToken,String customerId) throws Exception{
		
		String server =  FrontierWholesalesMagentoCommerceConnector.getServer();
		InputStream inputStream = Request.Get(server + "/rest/V1/customers/"+customerId+"/password/resetLinkToken/"+resetToken)
				.addHeader("Authorization", adminToken)
				.execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream);
			return response;
	}
	
}
