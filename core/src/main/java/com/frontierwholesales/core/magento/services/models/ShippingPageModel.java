package com.frontierwholesales.core.magento.services.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.beans.FrontierWholesalesShippingMethods;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Model(adaptables= {SlingHttpServletRequest.class})
public class ShippingPageModel extends BaseModel{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingPageModel.class);
	
	private List<FrontierWholesalesShippingMethods> shippingMethods;
	
	private ObjectMapper mapper = new ObjectMapper();
	private String cartObject;
	
	private String emailId;

	private FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	
	/**
	 * Service call for shipping
	 * 1) Call customer billing address to construct for billing address with email id (/rest/V1/customers/{customerid}/billingAddress)
	 * 2) Pass billing address as a request to to get cart address (/rest/V1/carts/mine/billing-address) - Returns ID
	 * 3) Call ShippingMethods to different delivery methods with codes (/rest/V1/carts/mine/shipping-methods)
	 */
	
	@Override
	  protected void init() {
		 LOGGER.debug("Init method");
		 try {
			 mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				
			 String adminToken = getAdminToken(slingHttpServletRequest);
			 
			 String userToken = getUserToken();
			
			String customer = connector.getCustomer(userToken);
			LOGGER.debug("customer id is "+customer);
			JsonObject customerObject = extractCustomerDetailsFromJsonObject(customer);
			LOGGER.debug("customer object is "+customerObject);
			String id = customerObject.get("id").getAsString();
			LOGGER.debug("id "+id);
			this.emailId = customerObject.get("email").getAsString();
			
			slingHttpServletRequest.getSession().setAttribute("EmailAddress", this.emailId);
			 String addressResponse = connector.getCustomerShippingAddress(adminToken, id);
			LOGGER.debug("address response is "+addressResponse);
			 slingHttpServletRequest.getSession().setAttribute("BillingAddress", addressResponse);
			 String billingAddress = createJSONObjectForBillingAddress(addressResponse,this.emailId);
			LOGGER.debug("billing response "+billingAddress);
			 String addressId = connector.cartBillingAddress(userToken, billingAddress);
			LOGGER.debug("address id is "+addressId);
			String shippingMethodsResponse =  connector.shippingMethods(userToken);
			 TypeReference<List<FrontierWholesalesShippingMethods>> typeReference = new TypeReference<List<FrontierWholesalesShippingMethods>>() {};
				
			this.shippingMethods = mapper.readValue(shippingMethodsResponse, typeReference);
			
			this.cartObject = connector.getCartTotal(userToken);
			slingHttpServletRequest.setAttribute("CartObject", cartObject);
		 }catch(Exception anyEx) {
			LOGGER.error("Error in Shipping page model "+anyEx.getMessage());
		}
	 }
	 
	 private JsonObject extractCustomerDetailsFromJsonObject(String jsonObject)throws Exception {
		 Gson gson = new Gson();
		 JsonElement element = gson.fromJson(jsonObject, JsonElement.class);
			JsonObject customerObject = element.getAsJsonObject();
			
			return customerObject;
	 }
	
	private String createJSONObjectForBillingAddress(String jsonObject,String emailAddress) throws Exception{
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(jsonObject, JsonElement.class);
		JsonObject customerAddress = element.getAsJsonObject();
		
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
		JsonObject parentObject = new JsonObject();
		parentObject.add("address", address);
		parentObject.addProperty("useForShipping", true);
		
		return parentObject.toString();
	}
	
	public List<FrontierWholesalesShippingMethods> getShippingMethods() {
		return shippingMethods;
	}
	
	public String getCartObject() {
		return cartObject;
	}
	
	public String getEmailId() {
		return emailId;
	}
	 
	 
}
