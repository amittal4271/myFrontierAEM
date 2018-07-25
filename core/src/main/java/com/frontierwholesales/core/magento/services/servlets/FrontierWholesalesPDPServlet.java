package com.frontierwholesales.core.magento.services.servlets;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

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
			
			String adminToken  = commerceConnector.getAdminToken();
			
			if ((productSku != null) && (productSku.length()>0)){
				System.out.println("DEBUG: "+adminToken);
				String productDetails = commerceConnector.getProductDetails(adminToken, productSku);		
				response.getOutputStream().println(parseJsonObject(productDetails,request));
				// TODO this may not be needed depending on how the final design of PDP looks
				setProductMasterProperty(request, productSku);
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
	    		"WHERE ISDESCENDANTNODE(node, \"/content/dam/FrontierImages/product/"+ productSku+"\")"; 
	    			
		
		Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");	   		   
	    QueryResult result = query.execute();	  
	    NodeIterator nodeIter = result.getNodes();
		   log.debug("before loop");
		   int count=1;
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
	        	String imgName = properties.get("cq:name", (String) null);	
	        	log.debug("image full path is "+imgName);
	        	String relativePath = properties.get("dam:relativePath",(String)null);
	        	if(relativePath != null) {
	        		log.debug("image name "+imgName);
	        		imgPath = "/content/dam/"+ relativePath;
	        		obj.addProperty("path", imgPath);
	        		count++;
	        		array.add(obj);
	        	}
	        	
	        	
	        }
           
	       
		    		

	    }
	    
	    return array;		    
	}
	
	private String parseJsonObject(String productDetails,SlingHttpServletRequest request) throws Exception{
		
		Gson json = new Gson();
		JsonElement element = json.fromJson(productDetails, JsonElement.class);
		
		JsonObject object = element.getAsJsonObject();
		boolean bInformation = false;
		
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
					
		JsonElement priceElement = object.get("price");
		object.addProperty("formattedPrice", "$"+priceFormat.format(priceElement.getAsDouble()));
		JsonElement skuElement = object.get("sku");
		object.add("imgPath", getImagePath(skuElement.getAsString(),request));
		
		JsonArray attributesArray = object.getAsJsonArray("custom_attributes");
		
		for(JsonElement attributesElement:attributesArray) {
			JsonObject attrObject = attributesElement.getAsJsonObject();
			JsonElement codeElement = attrObject.get("attribute_code");
			if(codeElement.getAsString().equals("special_price")) {
				
				object.addProperty("special_price", attrObject.get("value").getAsDouble());
			}
			if(codeElement.getAsString().equals("manufacturer")) {
				object.addProperty("brand", attrObject.get("value").getAsString());
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
		}
		
		object.addProperty("additionalInformation", bInformation);

		return object.toString();
	}
	
	private void setProductMasterProperty( SlingHttpServletRequest slingRequest, String sku ) {
		String currentPagePath = slingRequest.getParameter("currentPagePath");
		if( StringUtils.isNotBlank(currentPagePath) ) {
			// TODO change to use service user
			ResourceResolver resourceResolver = slingRequest.getResourceResolver();
			Resource currentProductPageResource = resourceResolver != null ? resourceResolver.getResource(currentPagePath) : null;
			Resource contentResource = currentProductPageResource != null ? currentProductPageResource.getChild(JcrConstants.JCR_CONTENT) : null;
			ModifiableValueMap currentProductPageProps = contentResource != null ? contentResource.adaptTo(ModifiableValueMap.class) : null; 
			String productMaster = currentProductPageProps != null ? currentProductPageProps.get("cq:productMaster", String.class) : StringUtils.EMPTY;
			// if there is not already the property on the page, find and set it
			if( StringUtils.isBlank(productMaster) ) {
				String productMasterPath = getCommerceProductPath(resourceResolver, sku);
				if( StringUtils.isNotBlank(productMasterPath) ) {
					currentProductPageProps.put("cq:productMaster", productMasterPath);
					try {
						resourceResolver.commit();
					} catch( PersistenceException persistEx ) {
						log.error("PersistenceException trying to save property to page:\n", persistEx);
					}
				}
			}
		}
	}
	
	private String getCommerceProductPath( ResourceResolver resourceResolver, String sku ) {
		String sqlStatement = new StringBuilder().append("SELECT * FROM [nt:unstructured] AS products")
								.append(" WHERE ISDESCENDANTNODE(products, '/etc/commerce/products')")
								.append(" AND [baseSku] = '").append(sku).append("' AND [cq:commerceType] = 'product'").toString();
		Session session = resourceResolver != null ? resourceResolver.adaptTo(Session.class) : null;
		try {
			QueryManager queryManager = session != null ? session.getWorkspace().getQueryManager() : null;
			Query commerceQuery = queryManager != null ? queryManager.createQuery(sqlStatement, Query.JCR_SQL2) : null;
			QueryResult queryResult = commerceQuery != null ? commerceQuery.execute() : null;
			NodeIterator commerceIterator = queryResult != null ? queryResult.getNodes() : null;
			if( commerceIterator != null && commerceIterator.hasNext() ) {
				Node commerceNode = commerceIterator.nextNode();
				String commerceProductPath = commerceNode.getPath();
				log.debug("cq:productMaster should be set to: {} for sku: {}", commerceProductPath, sku);
				return commerceProductPath;
			}
		} catch( RepositoryException repoEx ) {
			log.error("RepositoryException trying to execute search query for commerce product path:\n", repoEx);
		}
		return null;
	}
}	
	
