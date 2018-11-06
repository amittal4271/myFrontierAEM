package com.frontierwholesales.core.magento.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.google.gson.JsonObject;

public class FrontierWholesalesUserRegistration {

	 private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesUserRegistration.class);
	private  FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	 /**
	  * This is for both member and non member registration
	  * @param data
	  * @param server
	  * @return
	  * @throws Exception
	  */
	 public  String registration(String data,String authToken,String server) throws FrontierWholesalesBusinessException{
		 log.debug("registration Method start ");
		 connector.setServer(server);
		 String api="/rest/all/V1/frontier/account";
		 String customerId = connector.constructAPIMethod("Post", authToken, api, "registration", null, data);
		
		 log.debug("registration Method end ");
		 return customerId;
	 }
	
	 /**
	  * customer registration
	  * @param params
	  * @param server
	  * @return
	  * @throws Exception
	  */
	public  String customerRegistration(JsonObject params,String server) throws FrontierWholesalesBusinessException{
		log.debug("customerRegistration Method Start");
		connector.setServer(server);
		String api="/rest/V1/customers";
		 String customerId = connector.constructAPIMethod("PostWithoutToken", null, api, "customerRegistration", null, params.toString());
		
		log.debug("customerRegistration Method End");
		return customerId;
	}
	
	public  String companyRegistration(String pwd,JsonObject params,String server) throws FrontierWholesalesBusinessException{
		log.debug("companyRegistration Method Start");
		connector.setServer(server);
		String api="/rest/all/V1/company";
		 String userDetails = connector.constructAPIMethod("Post", pwd, api, "companyRegistration", null, params.toString());
		
		log.debug("companyRegistration Method End");
		return userDetails;
		
	}
	
	/**
	 * Get list of countries and regions
	 * @param adminToken
	 * @param server
	 * @return
	 * @throws Exception
	 */
	public  String getCountriesWithRegions(String adminToken,String server) throws FrontierWholesalesBusinessException{
		log.debug("getCountriesWithRegions Method Start");
		
		connector.setServer(server);
		String api="/rest/all/V1/directory/countries";
		 String countryAndRegions = connector.constructAPIMethod("Get", adminToken, api, "getCountriesWithRegions", null, null);
		
		log.debug("getCountriesWithRegions Method End");
		return countryAndRegions;
	}
	
	/**
	 * reset Password
	 * @param adminToken
	 * @param jsonData
	 * @param server
	 * @return
	 * @throws Exception
	 */
	public  String resetPassword(String adminToken,String jsonData,String server) throws FrontierWholesalesBusinessException{
		log.debug("resetPassword Method Start");
		connector.setServer(server);
		String api="/rest/V1/customers/resetPassword";
		String response = connector.constructAPIMethod("Post", adminToken, api, "resetPassword", null, jsonData);
		
		log.debug("resetPassword Method End");
		return response.replace("\"", "");
	}
	
	/**
	 * Update customer email id,first and last name
	 * @param adminToken
	 * @param jsonData
	 * @param id
	 * @param server
	 * @return
	 * @throws Exception
	 */
	public  String updateCustomers(String adminToken,String jsonData,String id,String server) throws FrontierWholesalesBusinessException{
		log.debug("updateCustomers Method Start");
		connector.setServer(server);
		String api="/rest/V1/customers/"+id;
		String response = connector.constructAPIMethod("Put", adminToken, api, "updateCustomers", null, jsonData);
		
		 log.debug("updateCustomers Method End");
		return response;
	}
	
	/** 
	 * add new address for logged users
	 * @param adminToken
	 * @param jsonData
	 * @param server
	 * @return
	 * @throws Exception
	 */
	public  String addAddress(String adminToken,String jsonData,String server) throws FrontierWholesalesBusinessException{
		log.debug("addAddress Method Start");
		connector.setServer(server);
		String api="/rest/all/V1/addNewAddress";
		String response = connector.constructAPIMethod("Post", adminToken, api, "addAddress", null, jsonData);
		
		 log.debug("addAddress Method End");
		return response;
	}
	
	/**
	 * Retrieve user details and address list for logged users
	 * @param userToken
	 * @param server
	 * @return
	 * @throws Exception
	 */
	public  String getWhoAmI(String userToken,String server) throws FrontierWholesalesBusinessException{
		connector.setServer(server);
		String api="/rest/all/V1/addNewAddress";
		return connector.constructAPIMethod("Get", userToken, api, "getWhoAmI", null, null);
		
	}
	
	/**
	 * 
	 * @param adminToken
	 * @param resetToken
	 * @param customerId
	 * @param server
	 * @return
	 * @throws FrontierWholesalesBusinessException
	 */
	public  String validateToken(String adminToken,String resetToken,String customerId,String server) throws FrontierWholesalesBusinessException{
		log.debug("validateToken Method Start");
		connector.setServer(server);
		String api="/rest/V1/customers/"+customerId+"/password/resetLinkToken/"+resetToken;
		String response = connector.constructAPIMethod("Get", adminToken, api, "validateToken", null, null);
		
		log.debug("validateToken Method End");
		return response;
	}
	
}
