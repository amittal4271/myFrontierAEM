package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.FrontierWholesalesUserRegistration;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



@Component(immediate = true,service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=FrontierWholesalesUserRegistration - Sling All Methods Servlet",
        "sling.servlet.methods={'GET','POST'}",
       "sling.servlet.selectors=data",
       "sling.servlet.paths=/services/registration",
        "sling.servlet.extensions=html"      
        
})
public class FrontierWholesalesUserRegistrationServlet  extends SlingAllMethodsServlet{

	 private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesUserRegistrationServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	private FrontierWholesalesUserRegistration registration = new FrontierWholesalesUserRegistration();
	private MagentoCommerceConnectorService config;
	@Reference
	public void activate(MagentoCommerceConnectorService config) {
		
		this.config = config;
	}
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		String object;
		JsonObject jsonObject = new JsonObject();
		
		try {
			
			String adminToken = config.getAppToken();
			String server = config.getServer();
			object = registration.getCountriesWithRegions(adminToken,server);
			
			response.getOutputStream().write(object.getBytes(FrontierWholesalesConstants.UTF8));
		}catch(FrontierWholesalesBusinessException bEx) {
			log.error("Exception occurred FrontierWholesalesBusinessException "+bEx.getMessage());
			jsonObject.addProperty(FrontierWholesalesConstants.ERROR, bEx.getMessage());
			response.getOutputStream().println(jsonObject.toString());
		}
		catch (Exception e) {
			log.error("Exception occurred in doGet method "+e.getMessage());
			e.printStackTrace();
			jsonObject.addProperty(FrontierWholesalesConstants.ERROR, "Error");
			response.getOutputStream().println(jsonObject.toString());
			
		}
		 
		
	}
	
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
			boolean bReturn = false;
			String credentials="";
			JsonObject jsonObject = new JsonObject();
			log.debug("entered into doPost method of registration");
			try {
				 String adminToken = config.getAppToken();
				String server = config.getServer();
				 String authorization = request.getHeader(FrontierWholesalesConstants.AUTHORIZATION);
			    if (authorization != null && authorization.startsWith("Basic")) {
			        // Authorization: Basic base64credentials
			        String base64Credentials = authorization.substring("Basic".length()).trim();
			         credentials = new String(Base64.getDecoder().decode(base64Credentials),
			                Charset.forName("UTF-8"));
			        
			        	
			        String action = request.getParameter("action");
			        // buyers club registration
			        if(action.equals("buyersClub")) {
			        	String resetPwdData = request.getParameter("resetPwd");
			        	String resetToken = request.getParameter("resetToken");
			        	String customerId = request.getParameter("customerId");
			        	
			        	String tokenValidateResponse = registration.validateToken(adminToken, resetToken, customerId,server);
			        	
			        	if(tokenValidateResponse.equals("true")) {
			        	
			        	JsonObject resetPwdObject = updateJSONObject(resetPwdData, "newPassword", credentials);
			        	
			        	//get customer id here
			        	String customerResponse = registration.resetPassword(adminToken, resetPwdObject.toString(),server);
			        	//update first and lastname with customer id
			        	log.debug("password is resetted successfully.");
			        	String customerData = request.getParameter("customer");
			        	
			        	String addressData= request.getParameter("address");
			        	
			        	JsonObject updatedAddressData = updateJSONObject(addressData,"customer_id",customerResponse,"address");
			        	
			        	JsonObject updatedCustomerData = updateJSONObject(customerData,"id",customerResponse,"customer");
			        	
			        	registration.updateCustomers(adminToken, updatedCustomerData.toString(), customerResponse,server);
			        	log.debug("customer is successfully updated.");
			        	
			        	String addressResponse = registration.addAddress(adminToken, updatedAddressData.toString(),server);
			        	log.debug("buyersclub is registered successfully");
			        	
			        	if(addressResponse != null) {
			        			bReturn = true;
							  	jsonObject.addProperty("BuyersAddress", addressResponse);
							  	
						  }else {
							 
							  log.error("Returned object is null ");
							  response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Address Response object is null");
						  }
			        	}else {
			        		jsonObject.addProperty("Fail", tokenValidateResponse);
			        		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Reset password token is mismatch");
			        	}
			        	
			        }else {
			        	
			        	String data = request.getParameter("registration");
			        	JsonObject updatedObject = updateJSONObject(data,"password",credentials);
			        	
			        	String customerId = registration.registration(updatedObject.toString(),adminToken,server);
			        	 jsonObject.addProperty("CustomerData", customerId);
			        	 bReturn = true;
			        	 log.debug("Successfully user is registered ");
			        	
			        }
			    }else {
			    	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password is not set");
			    }
			    if(bReturn) {
			    	String username = request.getParameter("email");
			    	connector.setServer(server);
			    	String userToken = connector.getToken(username, credentials);		    	
			    	
			    	 jsonObject.addProperty("UserToken", userToken);
			    	 response.getOutputStream().write(jsonObject.toString().getBytes("UTF-8"));
			    }
			}catch(Exception anyEx) {				
				log.error("Error is "+anyEx.getMessage());
				String msg = anyEx.getMessage();				
				 jsonObject.addProperty("Fail", msg);
		    	 response.getOutputStream().println(jsonObject.toString());
		    	
				
			}
			
		
		
	}
	
	private JsonObject updateJSONObject(String data,String key,String value) {
		log.debug("updateJSONObject method start");
		Gson json = new Gson();
		JsonElement element = json.fromJson(data, JsonElement.class);
		JsonObject object = element.getAsJsonObject();
		object.addProperty(key, value);
		log.debug("updateJSONObject method end");
		return object;
	}
	
	private JsonObject updateJSONObject(String data,String key,String value,String objName) {
		log.debug("updateJSONObject start");
		Gson json = new Gson();
		JsonElement element = json.fromJson(data, JsonElement.class);
		JsonObject object = element.getAsJsonObject();
		JsonElement objElement = object.get(objName);
		JsonObject jsonObject = objElement.getAsJsonObject();
		
		jsonObject.addProperty(key, value);
		
		
		
		object.add(objName, jsonObject);
		JsonElement updatedElement = json.fromJson(object, JsonElement.class);
		jsonObject = updatedElement.getAsJsonObject();
		log.debug("updateJSONObject end");
		return jsonObject;
	}
	

}
