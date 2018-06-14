package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.beans.FrontierWholesalesProductSearch;
import com.frontierwholesales.core.beans.FrontierWholesalesProducts;
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
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet FrontierWholesalesPDPServlet Start");
		try {
			//FrontierWholesalesProducts product = new FrontierWholesalesProducts();
			String productSku = request.getParameter("sku");
			log.debug("Product details for #sku: {}",productSku);
			
			String adminToken = getTokenFromSession(request);
			//String categories = commerceConnector.getCategories(adminToken, categoryId);
			if ((productSku != null) && (productSku.length()>0)){
				System.out.println("DEBUG: "+adminToken);
				String productDetails = commerceConnector.getProductDetails(adminToken, productSku);		
				response.getOutputStream().println(parseJsonObject(productDetails));
			}
			else {
				response.getOutputStream().println("Empty SKU provided");
			}
		}catch(Exception anyEx) {
			log.error("Error in pdpservlet {}", anyEx.getMessage());
			String errorJson="PDP Error";
			response.getOutputStream().println("Error");
		}
	}

	private String getTokenFromSession(SlingHttpServletRequest request) throws Exception{
		log.debug("getToken from session start");
		String adminToken = (String)request.getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_ADMIN_TOKEN);
		
		if(null == adminToken) {
			adminToken = commerceConnector.getAdminToken();
			
			request.getSession().setAttribute(FrontierWholesalesConstants.MAGENTO_ADMIN_TOKEN, adminToken);
		}
		log.debug("getToken from session end");
		return adminToken;
	}
	
	private String getProduct(String productSku,SlingHttpServletRequest request) throws Exception{
		
		Session session = request.getResourceResolver().adaptTo(Session.class);
		QueryManager queryManager = session.getWorkspace().getQueryManager();
		String path="";
		String imgPath="";
		String nodeTitle="";
		Resource res = request.getResource();
		ResourceResolver resourceResolver = request.getResourceResolver();
		//String productSku = productSku
		//String[] nameSplit = name.split("-");
		//String tmpname="MT02";
		
		String sqlStatement="SELECT * FROM [nt:unstructured] AS node\n" + 
	    		"WHERE ISDESCENDANTNODE(node, \"/etc/commerce/products/we-retail\")\n" + 
	    		"AND CONTAINS([baseSku],'"+ productSku+"') AND CONTAINS([cq:commerceType],'product')";		
		
		Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");	   		   
	    QueryResult result = query.execute();	  
	    NodeIterator nodeIter = result.getNodes();
		   
	    while ( nodeIter.hasNext() ) {
	    	Node node = nodeIter.nextNode();
	        path = node.getPath();
	        System.out.println("-DEBUG: "+path);
	        res = resourceResolver.getResource(path);	        

            ValueMap properties = res.adaptTo(ValueMap.class);
            nodeTitle = properties.get("jcr:title", (String) null);	
            System.out.println("-DEBUG: "+nodeTitle);
	        	    	
    		NodeIterator cNode = node.getNodes();
			while ( cNode.hasNext() ) {
				Node imgNode = cNode.nextNode();
				if(imgNode.getName().equals("image")) {
					imgPath = imgNode.getProperty("fileReference").getValue().getString();
				}				 				 
			}
		    		

	    }
	    
	    return imgPath;		    
	}
	
	private String parseJsonObject(String productDetails) throws Exception{
		
		Gson json = new Gson();
		JsonElement element = json.fromJson(productDetails, JsonElement.class);
		
		JsonObject object = element.getAsJsonObject();
		
		
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
					
		JsonElement priceElement = object.get("price");
		object.addProperty("formattedPrice", "$"+priceFormat.format(priceElement.getAsDouble()));
		
		JsonArray attributesArray = object.getAsJsonArray("custom_attributes");
		
		for(JsonElement attributesElement:attributesArray) {
			JsonObject attrObject = attributesElement.getAsJsonObject();
			JsonElement codeElement = attrObject.get("attribute_code");
			if(codeElement.getAsString().equals("image")) {
				object.addProperty("imgPath", attrObject.get("value").getAsString());
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
		}

		return object.toString();
	}
}	
	
