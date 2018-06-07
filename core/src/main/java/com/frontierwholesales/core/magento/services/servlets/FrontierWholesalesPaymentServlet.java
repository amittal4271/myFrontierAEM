package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@SuppressWarnings("serial")
@SlingServlet(label="FrontierWholesalesPaymentServlet - Sling All Methods Servlet", 
description="FrontierWholesales Payment Sling All Methods Servlet.", 
paths={"/services/payment"}, methods={"GET","POST"})
public class FrontierWholesalesPaymentServlet  extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesPaymentServlet.class);
	public FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
		String addressInfo = request.getParameter("address");
		
		String billingResponse = (String) request.getSession().getAttribute("BillingAddress");
	
		String emailId = (String) request.getSession().getAttribute("EmailAddress");
		
		String paymentaddress = createJSONObjectForBillingAddress(addressInfo,billingResponse,emailId);
		
		request.getSession().setAttribute("ShippingInfo", paymentaddress);
		}catch(Exception anyEx) {
			log.error("Error in payment servlet Get Method "+anyEx.getMessage());
		}
		
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			String userToken = (String)request.getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
			String paymentInfo = request.getParameter("payment");
			log.debug("payment info is "+paymentInfo);
			String paymentResponse = commerceConnector.submitPayment(userToken, paymentInfo);
			response.getOutputStream().print(paymentResponse);
		}catch(Exception anyEx) {
			log.error("Error during the Post operation in PaymentServlet ",anyEx,anyEx.getMessage());
			response.getOutputStream().print("Error");
		}
	}
	
	private String createJSONObjectForBillingAddress(String addressInfo,String billingResponse,String emailAddress) throws Exception{
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(billingResponse, JsonElement.class);
		JsonObject customerAddress = element.getAsJsonObject();
		log.debug("inside create method");
		JsonObject regionObject = customerAddress.getAsJsonObject("region");
		JsonElement arrayElement = customerAddress.get("street");
		
		JsonObject address = new JsonObject();
		address.addProperty("region", regionObject.get("region").getAsString());
		address.addProperty("region_id", regionObject.get("region_id").getAsString());
		address.addProperty("region_code", regionObject.get("region_code").getAsString());
		address.addProperty("country_id", customerAddress.get("country_id").getAsString());
		address.add("street", arrayElement);
		address.addProperty("telephone", customerAddress.get("telephone").getAsString());
		address.addProperty("postcode", customerAddress.get("postcode").getAsString());
		address.addProperty("city", customerAddress.get("city").getAsString());
		address.addProperty("firstname", customerAddress.get("firstname").getAsString());
		address.addProperty("lastname", customerAddress.get("lastname").getAsString());
		address.addProperty("email", emailAddress);
		log.debug("Address from json is "+address);
		JsonObject parentObject = new JsonObject();
		parentObject.add("billing_address", address);
		
		JsonElement addressElement = gson.fromJson(addressInfo,  JsonElement.class);
		JsonObject addressInfoObject = addressElement.getAsJsonObject();
		JsonObject object = addressInfoObject.getAsJsonObject("addressInformation");
		JsonObject shippingAddress = object.getAsJsonObject("shipping_address");
		shippingAddress.addProperty("email", emailAddress);
		object.add("billing_address", address);
		log.debug("final object is "+object.toString());
		addressInfoObject.add("addressInformation", object);
		return addressInfoObject.toString();
	}
}
