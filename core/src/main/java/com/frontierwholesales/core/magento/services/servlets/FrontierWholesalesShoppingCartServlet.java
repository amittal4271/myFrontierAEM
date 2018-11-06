package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesErrorCode;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("serial")


@Component(immediate = true,service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=FrontierWholesalesShoppingCart - Sling All Methods Servlet",
        "sling.servlet.methods={'GET','POST'}",
       "sling.servlet.selectors=data",
       "sling.servlet.paths=/services/cart",
        "sling.servlet.extensions=html"      
        
})
public class FrontierWholesalesShoppingCartServlet  extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesShoppingCartServlet.class);
	
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	private ObjectMapper mapper = new ObjectMapper();
	private FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
	private MagentoCommerceConnectorService config;
	@Reference
	public void activate(MagentoCommerceConnectorService config) {
		
		this.config = config;
	}
	
	@Override
	public void init() {
		 mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	}
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FrontierWholesalesShoppingCartServlet doGet method Start");
		
		
		try {
			String action = request.getParameter("action");
			log.debug(" action is "+action);
			String token = request.getHeader("Authorization");
			String adminToken = this.config.getAppToken();
			String serverName = this.config.getServer();
			commerceConnector.setServer(serverName);			
			if(token == null) {
			
				throw new FrontierWholesalesBusinessException("token is null",FrontierWholesalesErrorCode.TOKEN_ERROR);
			
			}
		
			if(action.equals("remove")) {
				String itemId = request.getParameter("itemId");
				
				 commerceConnector.removeCartItem(token, itemId);
				
				
			}else if(action.equals("add")){
				String jsonData = request.getParameter("items");
			
				// create cart
				String cartId = commerceConnector.initCart(token);
				
				//update json structure with cartid
				String updatedData = FrontierWholesalesUtils.updateJsonObject(jsonData, "cartItem", "quote_id", cartId);
				//add item into the cart
				commerceConnector.addItemToCart(token, updatedData);
				
				commerceConnector.initCart(token);
			}
			String cartObject = commerceConnector.getCartTotalWithItems(token);
			String customerId = utils.getCustomerDetailsByParameter("id", token,serverName);
			
			String roleObject = commerceConnector.getUserRole(adminToken, customerId);
			
			String roleName = getRoleName(roleObject);
			
			
			String object = getValueFromJson(cartObject,request,roleName);
			
			log.debug("shopping cart operations end ");
			
			response.getOutputStream().write(object.getBytes(FrontierWholesalesConstants.UTF8));
			
		}catch(Exception anyEx) {
			log.debug("Error "+anyEx.getMessage());
			
			JsonObject errorJsonObject = new JsonObject();
			errorJsonObject.addProperty("Fail", anyEx.getMessage());
			response.getOutputStream().println(errorJsonObject.toString());
		}
		log.debug("FrontierWholesalesShoppingCartServlet doGet method End");
	}
	
	
	
	private String getRoleName(String object) throws Exception{
		log.debug("getRoleName start");
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(object).getAsJsonObject();
		String roleName="";
		JsonElement attribElement = jsonObject.get("extension_attributes");
		JsonElement roleObject = attribElement.getAsJsonObject().get("role_name");
		if(roleObject != null) {
			roleName = roleObject.getAsString();
		}
		log.debug("getRoleName end");
		return roleName;
	}
	
	private String getValueFromJson(String cartObject,SlingHttpServletRequest request,String roleName) throws Exception{
		log.debug("FrontierWholesalesShoppingCartServlet getValueFromJson method Start");
		Gson json = new Gson();
		JsonElement element = json.fromJson(cartObject,JsonElement.class);
		
		JsonObject object = element.getAsJsonObject();
		JsonElement quoteElement = object.get("quote");
		
		JsonObject quote = quoteElement.getAsJsonObject();
		FrontierWholesalesUtils _utils = new FrontierWholesalesUtils();
		JsonArray array = object.getAsJsonArray("item_details");
	
		JsonArray updatedArray = new JsonArray();
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
		
		for(int i=0;i<array.size();i++) {
			JsonElement jsonElement = array.get(i);
			JsonObject itemObject = jsonElement.getAsJsonObject();
			JsonElement price = itemObject.get("price");
			
			String sku = itemObject.get("sku").getAsString();
			
			String imgPath = _utils.getImagePath(sku, request);
			itemObject.addProperty("imgPath", imgPath);
			

			itemObject.addProperty("price", "$"+priceFormat.format(price.getAsDouble()));
			JsonElement qtyObject = itemObject.get("qty");
			boolean bReturn = false;
			if(qtyObject.getAsInt() > 1) {
				bReturn = true;
			}
			itemObject.addProperty("quantities", bReturn);
			

			JsonElement updatedElement = json.fromJson(itemObject, JsonElement.class);
			updatedArray.add(updatedElement);
			

		}
		
		
			JsonElement arrayElement = json.fromJson(updatedArray, JsonElement.class);
		
			JsonElement subTotal = quote.get("subtotal");
			
			double subtotal = 0;
			if(subTotal != null) {
				
				subtotal = subTotal.getAsDouble();
			}
			
			if(subtotal == 0) {
				
				object.addProperty("noTotal", true);
			}else {
			
				object.addProperty("noTotal", false);
			}
			
			object.addProperty("subTotal", "$"+priceFormat.format(subtotal));
			
			object.add("items", arrayElement);
			
			object.addProperty("role_name", roleName);
			
			log.debug("FrontierWholesalesShoppingCartServlet getValueFromJson method End");
			return object.toString();
			
		
	}

}
