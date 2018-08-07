package com.frontierwholesales.core.magento.services.servlets;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@SlingServlet(label="FrontierWholesalesUserRegistration - Sling All Methods Servlet", 
description="FrontierWholesales Product details Sling All Methods Servlet.", 
paths={"/services/pdp"}, methods={"GET"})
public class FrontierWholesalesPDPServlet  extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesPDPServlet.class);	
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	private FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet FrontierWholesalesPDPServlet Start");
		try {
			
			final String authorization = request.getHeader("Authorization");
			
			String groupId ="";
			
			
			String productSku = request.getParameter("sku");
			log.debug("Product details for #sku: {}",productSku);
			
			String adminToken  = commerceConnector.getAdminToken();
			
			if ((productSku != null) && (productSku.length()>0)){
				
				String productDetails = commerceConnector.getProductDetails(adminToken, productSku);
				
				
				if(authorization != null) {
					groupId = utils.getCustomerDetailsByParameter("group_id", authorization);
				}
				
				response.getOutputStream().println(parseJsonObject(productDetails,request,groupId));
				
			}
			else {
				response.getOutputStream().println("Empty SKU provided");
			}
		}catch(Exception anyEx) {
			log.error("Error in pdpservlet {}", anyEx.getMessage());
			
			response.getOutputStream().println("Error");
		}
	}

	
	
private JsonArray getImagePath(String productSku,SlingHttpServletRequest request) throws Exception{
		
		Session session = request.getResourceResolver().adaptTo(Session.class);
		QueryManager queryManager = session.getWorkspace().getQueryManager();
		String path="";
		String imgPath="";
		//String nodeTitle="";
		Resource res = request.getResource();
		ResourceResolver resourceResolver = request.getResourceResolver();
		
		JsonArray array = new JsonArray();
		
		String sqlStatement="SELECT * FROM [nt:unstructured] AS node\n" + 
	    		"WHERE ISDESCENDANTNODE(node, \"/content/dam/FrontierImages/product/"+ productSku+"\")"
	    				+ "ORDER BY node.title"; 
	    			
		
		Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");	   		   
	    QueryResult result = query.execute();	  
	    NodeIterator nodeIter = result.getNodes();
		   log.debug("before loop");
		  
	    while ( nodeIter.hasNext() ) {
	    	JsonObject obj = new JsonObject();
	    	Node node = nodeIter.nextNode();
	        path = node.getPath();
	        log.debug("inside loop: "+path);
	        res = resourceResolver.getResource(path);	        
	        String name = node.getName();
	        if(name.equals("jcr:content")) {
	        	log.debug("INSIDE jcr:content");
	        	ValueMap properties = res.adaptTo(ValueMap.class);
	        
		        	String relativePath = properties.get("dam:relativePath",(String)null);
		        	if(relativePath != null) {
		        		
		        		imgPath = "/content/dam/"+ relativePath;
		        		obj.addProperty("path", imgPath);
		        	
		        		array.add(obj);
		        	}
	        	
	        	
	        	
	        }
           
	       
		    		

	    }
	    if(array.size() == 0) {
	    	JsonObject obj = new JsonObject();
	    	obj.addProperty("path", "/content/dam/FrontierImages/default_product_image.jpg");
	    	array.add(obj);
	    }
	    return array;		    
	}
	
	private String parseJsonObject(String productDetails,SlingHttpServletRequest request,String groupId) throws Exception{
		
		Gson json = new Gson();
		JsonElement element = json.fromJson(productDetails, JsonElement.class);
		
		JsonObject object = element.getAsJsonObject();
		boolean bInformation = false;
		
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
					
		JsonElement priceElement = object.get("price");
		object.addProperty("formattedPrice", "$"+priceFormat.format(priceElement.getAsDouble()));
		JsonElement skuElement = object.get("sku");
		
	
		object.add("imgPath", getImagePath(skuElement.getAsString(),request));
	
		JsonObject extnObject = object.getAsJsonObject("extension_attributes");
		
		JsonArray extnArray = extnObject.getAsJsonArray("external_attributes");
		
		if(extnArray != null) {
			for(JsonElement extAttributes:extnArray) {
				JsonObject obj = extAttributes.getAsJsonObject();
				JsonElement attrCode = obj.get("attribute_code");
				if(attrCode !=null && attrCode.getAsString().equals("manufacturer")) {
					object.addProperty("brand", obj.get("name").getAsString());
				}
			}
		}
		
		JsonArray attributesArray = object.getAsJsonArray("custom_attributes");
		JsonArray tierPrices = object.getAsJsonArray("tier_prices");
		
		for(JsonElement pricesElement:tierPrices) {
			
			JsonObject obj = pricesElement.getAsJsonObject();
			
			JsonElement customerGroupElement = obj.get("customer_group_id");
		
			if(customerGroupElement.getAsString().equals(groupId)) {
				
				object.addProperty("tierprice", obj.get("value").getAsDouble());
			}
		}
		
		for(JsonElement attributesElement:attributesArray) {
			JsonObject attrObject = attributesElement.getAsJsonObject();
			JsonElement codeElement = attrObject.get("attribute_code");
			if(codeElement.getAsString().equals("special_price")) {
				
				object.addProperty("special_price", attrObject.get("value").getAsDouble());
			}
			
			if(codeElement.getAsString().equals("description")) {
				object.addProperty("description", attrObject.get("value").getAsString());
			}
			
			if(codeElement.getAsString().equals("small_image")) {
				object.addProperty("small_image", attrObject.get("value").getAsString());
			}
			if(codeElement.getAsString().equals("thumbnail")) {
				object.addProperty("thumbnail", attrObject.get("value").getAsString());
			}
			
			if(codeElement.getAsString().equals("barcode")) {
				object.addProperty("barcode", attrObject.get("value").getAsString());
			}
		
			if(codeElement.getAsString().equals("short_description")) {
				object.addProperty("shortDescription", attrObject.get("value").getAsString());
			}
		
			if(codeElement.getAsString().equals("ingredients")) {
				
				object.addProperty("ingredients", attrObject.get("value").getAsString());
			}
			
			if(codeElement.getAsString().equals("directions")) {
				
				object.addProperty("directions", attrObject.get("value").getAsString());
			}
		
			if(codeElement.getAsString().equals("features")) {
				
				object.addProperty("features", attrObject.get("value").getAsString());
			}
			
			if(codeElement.getAsString().equals("sug_uses")) {
				
				object.addProperty("sug_uses", attrObject.get("value").getAsString());
			}
	
			if(codeElement.getAsString().equals("pkg_contents")) {
				
				object.addProperty("pkg_contents", attrObject.get("value").getAsString());
			}
		
			if(codeElement.getAsString().equals("safety_info")) {
				
				object.addProperty("safety_info", attrObject.get("value").getAsString());
			}
		
			if(codeElement.getAsString().equals("new_product")) {
				object.addProperty("new_product", attrObject.get("value").getAsString());
			}
		
			if(codeElement.getAsString().equals("close_out")) {
				object.addProperty("close_out", attrObject.get("value").getAsString());
			}
		
			if(codeElement.getAsString().equals("on_sale")) {
				object.addProperty("on_sale", attrObject.get("value").getAsString());
			}
			
			if(codeElement.getAsString().equals("bulk")){
				object.addProperty("bulk",  attrObject.get("value").getAsString());
			}
			
			if(codeElement.getAsString().equals("map")){
				object.addProperty("map",  attrObject.get("value").getAsString());
			}
			
			if(codeElement.getAsString().equals("dea_required")) {
				object.addProperty("product_restriction",  attrObject.get("value").getAsInt());
			}
		}
	
		object.addProperty("additionalInformation", bInformation);

		return object.toString();
	}
	
	
	
	
}	
	
