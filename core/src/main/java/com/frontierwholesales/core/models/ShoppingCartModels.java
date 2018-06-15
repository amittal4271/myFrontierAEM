package com.frontierwholesales.core.models;

import java.text.DecimalFormat;
import java.util.List;

import javax.jcr.Session;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Getting cart list for logged or guest users
 * @author barani.ramakrishnan
 *
 */
public class ShoppingCartModels extends WCMUsePojo{

	
	public FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartModels.class);
	
	private  List productList;
	
	private int listSize;
	
	private String totalPrice;
	
	private Object object;
	
	private Session session;
    
	//Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	private ObjectMapper mapper = new ObjectMapper();
	@Override
	public void activate() throws Exception {
		try {
		 mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		
		
		
		String token = (String)getRequest().getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
		
		if(token == null) {
		//get user token here
		
		throw new Exception("token is null");
		
		}
				
		String cartObject = commerceConnector.getCartTotal(token);
		
		TypeReference<Object> typeReference = new TypeReference<Object>() {};
		
		this.object = mapper.readValue(cartObject, typeReference);
		//Resource resource = getResource();
		//getQueryResults(resource);
		}catch(Exception anyEx) {
			LOGGER.error("Exception in activate ");
			
			anyEx.printStackTrace();
		}
		
	}
	
	
	
	public String calculatePriceTotal(String cartList) {
		Gson json = new Gson();
		JsonElement element = json.fromJson(cartList,JsonElement.class);
		JsonArray array = element.getAsJsonArray();
		JsonArray convert = new JsonArray();
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
		double totalPrice =0.00;
		for(int i=0;i<array.size();i++) {
			JsonElement jsonElement = array.get(i);
			
			JsonObject cartObject = jsonElement.getAsJsonObject();
			
			JsonElement price = cartObject.get("price");
			JsonElement qty = cartObject.get("qty");
			double dPrice = price.getAsDouble();
			int cartQty = qty.getAsInt();
			cartObject.addProperty("convertedPrice",priceFormat.format(dPrice) );
			
			double qtyPrice = dPrice * cartQty;
			cartObject.addProperty("qtyPrice",priceFormat.format(qtyPrice ));
			
			totalPrice = totalPrice + qtyPrice;
			
			JsonElement updatedElement = json.fromJson(cartObject, JsonElement.class);
			convert.add(updatedElement);
			this.listSize = this.listSize + cartQty;
		}
		this.totalPrice = priceFormat.format(totalPrice);
		return convert.toString();
		
		
	}

	
	public Object getObject() {
		return object;
	}

}
