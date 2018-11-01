package com.frontierwholesales.core.magento.services;

import java.io.InputStream;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.JsonObject;

public class FrontierWholesalesUserRegistration {

	 private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesUserRegistration.class);
	private static FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	 /**
	  * This is for both member and non member registration
	  * @param data
	  * @return
	  * @throws Exception
	  */
	 public static String registration(String data,String authToken) throws Exception{
		 log.debug("registration Method start ");
		
		 String api="/rest/all/V1/frontier/account";
		 String customerId = connector.constructAPIMethod("Post", authToken, api, "registration", null, data);
			/*InputStream inputStream = Request.Post(server + "/rest/all/V1/frontier/account")
					.addHeader(FrontierWholesalesConstants.AUTHORIZATION,authToken)
	                .bodyString(data,ContentType.APPLICATION_JSON)
	                .execute().returnResponse().getEntity().getContent();
			 String customerId = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"registration");*/
		 log.debug("registration Method end ");
		 return customerId;
	 }
	
	 /**
	  * customer registration
	  * @param params
	  * @return
	  * @throws Exception
	  */
	public static String customerRegistration(JsonObject params) throws Exception{
		log.debug("customerRegistration Method Start");
		
		String api="/rest/V1/customers";
		 String customerId = connector.constructAPIMethod("PostWithoutToken", null, api, "customerRegistration", null, params.toString());
		/*InputStream inputStream = Request.Post(server + "/rest/V1/customers")
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnResponse().getEntity().getContent();
		 String customerId = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"customerRegistration");*/
		log.debug("customerRegistration Method End");
		return customerId;
	}
	
	public static String companyRegistration(String pwd,JsonObject params) throws Exception{
		log.debug("companyRegistration Method Start");
		
		String api="/rest/all/V1/company";
		 String userDetails = connector.constructAPIMethod("Post", pwd, api, "companyRegistration", null, params.toString());
		/*InputStream inputStream = Request.Post(server + "/rest/all/V1/company")
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION,pwd)
                .bodyString(params.toString(),ContentType.APPLICATION_JSON)
                .execute().returnResponse().getEntity().getContent();
		 String userDetails = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"companyRegistration");*/
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
		
		
		String api="/rest/all/V1/directory/countries";
		 String countryAndRegions = connector.constructAPIMethod("Get", adminToken, api, "getCountriesWithRegions", null, null);
		/*InputStream inputStream = Request.Get(server + "/rest/all/V1/directory/countries")
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION, adminToken)
               
				.execute().returnResponse().getEntity().getContent();
		 String countryAndRegions = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getCountriesWithRegions");*/
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
		
		String api="/rest/V1/customers/resetPassword";
		String response = connector.constructAPIMethod("Post", adminToken, api, "resetPassword", null, jsonData);
		
		/*InputStream inputStream =  Request.Post(server + "/rest/V1/customers/resetPassword")
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION, adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
				.execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"resetPassword");*/
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
		String api="/rest/V1/customers/"+id;
		String response = connector.constructAPIMethod("Put", adminToken, api, "updateCustomers", null, jsonData);
		
		/*InputStream inputStream =  Request.Put(server + "/rest/V1/customers/"+id)
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION, adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
				 .execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"updateCustomers");*/
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
		String api="/rest/all/V1/addNewAddress";
		String response = connector.constructAPIMethod("Post", adminToken, api, "addAddress", null, jsonData);
		
		/*InputStream inputStream =Request.Post(server + "/rest/all/V1/addNewAddress")
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION, adminToken)
				 .bodyString(jsonData,ContentType.APPLICATION_JSON)
				 .execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"addAddress");*/
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
		String api="/rest/all/V1/addNewAddress";
		String response = connector.constructAPIMethod("Get", userToken, api, "getWhoAmI", null, null);
		
		/*InputStream inputStream = Request.Get(server + "/rest/V1/customers/me")
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION, userToken)
				
				.execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"getWhoAmI");*/
		return response;
	}
	
	public static String validateToken(String adminToken,String resetToken,String customerId) throws Exception{
		log.debug("validateToken Method Start");
		String api="/rest/V1/customers/"+customerId+"/password/resetLinkToken/"+resetToken;
		String response = connector.constructAPIMethod("Get", adminToken, api, "validateToken", null, null);
		
		/*String server =  FrontierWholesalesMagentoCommerceConnector.getServer();
		InputStream inputStream = Request.Get(server + "/rest/V1/customers/"+customerId+"/password/resetLinkToken/"+resetToken)
				.addHeader(FrontierWholesalesConstants.AUTHORIZATION, adminToken)
				.execute().returnResponse().getEntity().getContent();
		 String response = FrontierWholesalesUtils.parseMagentoResponseObject(inputStream,"validateResetToken");*/
		log.debug("validateToken Method End");
		return response;
	}
	
}
