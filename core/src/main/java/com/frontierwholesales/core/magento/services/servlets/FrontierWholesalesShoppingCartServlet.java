package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@SlingServlet(label="FrontierWholesalesUserRegistration - Sling All Methods Servlet", 
description="FrontierWholesales User Registration Sling All Methods Servlet.", 
paths={"/services/cart"}, methods={"GET","POST","DELETE","PUT"})
public class FrontierWholesalesShoppingCartServlet  extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesShoppingCartServlet.class);
	
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	private ObjectMapper mapper = new ObjectMapper();
	
	
	
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
			
			String token = (String)request.getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
			
			if(token == null) {
			
				throw new Exception("token is null");
			
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
				String cartItems = commerceConnector.addItemToCart(token, updatedData);
				
				
			}
			String cartObject = commerceConnector.getCartTotal(token);
			
			String object = getValueFromJson(cartObject,request);
			response.getOutputStream().println(object);
			
		}catch(Exception anyEx) {
			
			log.error("Error "+anyEx.getMessage());
			String errorJson="Error in Cart";
			response.getOutputStream().println(errorJson.toString());
			
		}
		log.debug("FrontierWholesalesShoppingCartServlet doGet method End");
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private String getImagePath(String name,SlingHttpServletRequest request) throws Exception{
		
		Session session = request.getResourceResolver().adaptTo(Session.class);
		QueryManager queryManager = session.getWorkspace().getQueryManager();
		String path="";
		String[] nameSplit = name.split("-");
		
		String sqlStatement="SELECT * FROM [nt:unstructured] AS node\n" + 
	    		"WHERE ISDESCENDANTNODE(node, \"/etc/commerce/products/we-retail\")\n" + 
	    		"AND CONTAINS([jcr:title],'"+ nameSplit[0]+"')";
		
		 Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");
		   
		   
		    QueryResult result = query.execute();
		  
		    NodeIterator nodeIter = result.getNodes();
		   
		    while ( nodeIter.hasNext() ) {
		    	Node node = nodeIter.nextNode();
		    	if(nameSplit.length > 1) {
			    	if(node.getName().equals(nameSplit[2])) {
			    		NodeIterator cNode = node.getNodes();
			    		 while ( cNode.hasNext() ) {
			    			 Node imgNode = cNode.nextNode();
			    			 if(imgNode.getName().equals("image")) {
			    				 path = imgNode.getProperty("fileReference").getValue().getString();
			    			 }
			    			 
			    			 
			    		 }
			    		
			    		}
			    	}else {
			    		NodeIterator cNode = node.getNodes();
			    		 while ( cNode.hasNext() ) {
			    			 Node imgNode = cNode.nextNode();
			    			 if(imgNode.getName().equals("image")) {
			    				path = imgNode.getProperty("fileReference").getValue().getString();
			    			 }
			    			 
			    			 
			    		 }
			    		
			    	}
		    }
		    
		  return path;
		    
	}
	
	private String getValueFromJson(String cartObject,SlingHttpServletRequest request) throws Exception{
		log.debug("FrontierWholesalesShoppingCartServlet getValueFromJson method Start");
		Gson json = new Gson();
		JsonElement element = json.fromJson(cartObject,JsonElement.class);
		
		JsonObject object = element.getAsJsonObject();
		
		JsonElement items = object.get("items");
		
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
		
		JsonArray updatedArray = new JsonArray();
		
		JsonArray array = items.getAsJsonArray();
		
		for(int i=0;i<array.size();i++) {
			JsonElement jsonElement = array.get(i);
			JsonObject itemObject = jsonElement.getAsJsonObject();
			JsonElement name = itemObject.get("name");
			
			String path = getImagePath(name.getAsString(),request);
			itemObject.addProperty("imgPath", path);
			
			JsonElement price = itemObject.get("price_incl_tax");
			JsonElement rowTotal = itemObject.get("row_total_incl_tax");
			
			JsonElement qtyObject = itemObject.get("qty");
			boolean bReturn = false;
			if(qtyObject.getAsInt() > 1) {
				bReturn = true;
			}
			itemObject.addProperty("quantities", bReturn);
			itemObject.addProperty("price", "$"+priceFormat.format(price.getAsDouble()));
			itemObject.addProperty("rowTotal", "$"+priceFormat.format(rowTotal.getAsDouble()));
			
			JsonElement updatedElement = json.fromJson(itemObject, JsonElement.class);
			updatedArray.add(updatedElement);
			
		}
		
			JsonElement arrayElement = json.fromJson(updatedArray, JsonElement.class);
		
			JsonElement subTotal = object.get("subtotal");
			
			object.addProperty("subTotal", "$"+priceFormat.format(subTotal.getAsDouble()));
			
			object.add("items", arrayElement);
			log.debug("FrontierWholesalesShoppingCartServlet getValueFromJson method End");
			return object.toString();
			
		
	}

}
