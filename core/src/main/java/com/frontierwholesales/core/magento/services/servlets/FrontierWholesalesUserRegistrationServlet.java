package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.http.entity.StringEntity;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.FrontierWholesalesUserRegistration;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



@SlingServlet(label="FrontierWholesalesUserRegistration - Sling All Methods Servlet", 
description="FrontierWholesales User Registration Sling All Methods Servlet.", 
paths={"/services/registration"}, methods={"GET","POST"})
public class FrontierWholesalesUserRegistrationServlet  extends SlingAllMethodsServlet{

	 private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesUserRegistrationServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		String object;
		JsonObject jsonObject = new JsonObject();
		
		try {
			
			String adminToken = (String)getTokenFromSession(request); 
			object = FrontierWholesalesUserRegistration.getCountriesWithRegions(adminToken);
			response.getOutputStream().println(object);
		} catch (Exception e) {
			log.error("Exception occurred in doGet method "+e.getMessage());
			e.printStackTrace();
			jsonObject.addProperty("Error", "Error");
			response.getOutputStream().println(jsonObject.toString());
			
		}
		 
		
	}
	
	private String getTokenFromSession(SlingHttpServletRequest request) throws Exception{
		log.debug("getToken from session start");
		String adminToken = (String)request.getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_ADMIN_TOKEN);
		if(null == adminToken) {
			adminToken = connector.getAdminToken();
			request.getSession().setAttribute(FrontierWholesalesConstants.MAGENTO_ADMIN_TOKEN, adminToken);
		}
		log.debug("getToken from session end");
		return adminToken;
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
			JsonObject jsonObject = new JsonObject();
			log.debug("entered into doPost method of registration");
			try {
			  String data = request.getParameter("customer");
				final String authorization = request.getHeader("Authorization");
			    if (authorization != null && authorization.startsWith("Basic")) {
			        // Authorization: Basic base64credentials
			        String base64Credentials = authorization.substring("Basic".length()).trim();
			        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
			                Charset.forName("UTF-8"));
					
					  String company = request.getParameter("company");
					 
					  JsonObject customerObject = updateJSONObject(data,"password",credentials);
					 
					  //Call customer service to get customer id here
					  String customerId = FrontierWholesalesUserRegistration.customerRegistration(customerObject);
					
					String adminPwd = (String)getTokenFromSession(request); 
					
					 String id = getCustomerId(customerId);
					
					 JsonObject companyObject = updateCompanyJSONObject(company,"super_user_id",id);
					
					  //call company service here to register
					  String registredValues = FrontierWholesalesUserRegistration.companyRegistration(adminPwd, companyObject);
					  log.debug("Successfully user is registered");
					  jsonObject.addProperty("Success", registredValues);
					  response.getOutputStream().println(jsonObject.toString());
			    }else {
			    	response.sendError(HttpServletResponse.SC_FORBIDDEN, "Password is not set");
			    }
			}catch(Exception anyEx) {
				log.error("Error is "+anyEx.getMessage());
				anyEx.printStackTrace();
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error "+anyEx.getMessage());
				
			}
			
		
		
	}
	
	private JsonObject updateJSONObject(String data,String key,String value) throws Exception{
		Gson json = new Gson();
		JsonElement element = json.fromJson(data, JsonElement.class);
		JsonObject object = element.getAsJsonObject();
		object.addProperty(key, value);
		return object;
	}
	
	private JsonObject updateCompanyJSONObject(String data,String key,String value) throws Exception{
		Gson json = new Gson();
		JsonElement element = json.fromJson(data, JsonElement.class);
		JsonObject object = element.getAsJsonObject();
		JsonElement companyElement = object.get("company");
		JsonObject companyObject = companyElement.getAsJsonObject();
		
		companyObject.addProperty(key, value);
		
		
		
		object.add("company", companyObject);
		JsonElement updatedElement = json.fromJson(object, JsonElement.class);
		companyObject = updatedElement.getAsJsonObject();
		return companyObject;
	}
	
	private String getCustomerId(String data) throws Exception{
		Gson json = new Gson();
		JsonElement element = json.fromJson(data, JsonElement.class);
		JsonObject object = element.getAsJsonObject();
		JsonElement idElement =  object.get("id");
		return idElement.getAsString();
	}

}
