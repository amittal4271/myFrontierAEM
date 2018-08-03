package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.FrontierWholesalesProductSearch;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")

@Component(immediate = true,service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=FrontierWholesales Product List Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
       "sling.servlet.selectors=data",
       "sling.servlet.paths=/services/productlist",
        "sling.servlet.extensions=html"      
        
})


public class FrontierWholesalesProductListServlet extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesProductListServlet.class);
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	private FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("doGet FrontierWholesalesProductListServlet Start here ");
		RequestPathInfo path = request.getRequestPathInfo();
		
		try {
			final String authorization = request.getHeader("Authorization");
			
			String groupId ="";
			
		   
			FrontierWholesalesProductSearch search = new FrontierWholesalesProductSearch();
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			search.setCurrentPage(currentPage);
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			search.setCategoryId(categoryId);
			int noOfRecsPerPage = Integer.parseInt(request.getParameter("noOfRecsPerPage"));
			search.setNoOfRecsPerPage(noOfRecsPerPage);
			String sortByPrice = request.getParameter("sortByPrice");
			search.setSortByPrice(sortByPrice);
			String sortByFeatured = request.getParameter("sortByFeatured");
			search.setSortByFeatured(sortByFeatured);
			String sortByNewProduct = request.getParameter("newProduct");
			search.setSortByNewProduct(sortByNewProduct);
			String facetSearch = request.getParameter("facetQuery");
			search.setFacetSearchQuery(facetSearch);
			if(authorization != null) {				
				groupId = utils.getCustomerDetailsByParameter("group_id", authorization);
				
			}
			
			String adminToken = commerceConnector.getAdminToken();
			
			String productList = commerceConnector.getProducts(adminToken, search);
			
			String catList = commerceConnector.getParentChildrenCategories(adminToken, categoryId);
			response.setContentType("text/html;charset=UTF-8;");
			response.setCharacterEncoding("UTF-8");
			response.getOutputStream().write(parseJsonObject(productList,catList,noOfRecsPerPage,currentPage,request,groupId).getBytes("UTF-8"));
		}catch(Exception anyEx) {
			log.error("Error in productList "+anyEx.getMessage());
			response.getOutputStream().println("Error");
		}
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("doPost FrontierWholesalesProductListServlet Start here ");
		try {
			
			String adminToken = commerceConnector.getAdminToken();
			
			String jsonData = request.getParameter("wishlist");
			String wishList = commerceConnector.addItemToWishList(adminToken, jsonData);
			JsonObject object = new JsonObject();
			if(wishList != null) {
			
				object.addProperty("WishList", "Added To WishList");
			}
			response.getOutputStream().println(object.toString());
		}catch(Exception anyEx) {
			log.error("Error in productList "+anyEx.getMessage());
			response.getOutputStream().println("Error");
		}
		
		log.debug("doPost FrontierWholesalesProductListServlet End here ");
	}
	
	
	private String parseJsonObject(String productList,String catList,int recsPerPage,int currentPage,
			SlingHttpServletRequest request,String groupId) throws Exception{
		
		Gson json = new Gson();
		JsonElement element = json.fromJson(productList, JsonElement.class);
		
		
		JsonObject object = element.getAsJsonObject();
		JsonArray itemArray = object.getAsJsonArray("items");
		
		for(int i=0;i<itemArray.size();i++) {
			
			JsonElement itemElement = itemArray.get(i);
			JsonObject itemObject = itemElement.getAsJsonObject();
			
			JsonElement skuElement = itemObject.get("sku");
			
			itemObject.addProperty("imgPath", getImagePath(skuElement.getAsString(),request));
			JsonArray attributesArray = itemObject.getAsJsonArray("custom_attributes");
			JsonArray tierPrices = itemObject.getAsJsonArray("tier_prices");
			for(JsonElement attributesElement:attributesArray) {
				JsonObject attrObject = attributesElement.getAsJsonObject();
				JsonElement codeElement = attrObject.get("attribute_code");
				
				
				
				if(codeElement.getAsString().equals("new_product")) {
					itemObject.addProperty("new_product", attrObject.get("value").getAsString());
				}
				
				if(codeElement.getAsString().equals("close_out")) {
					itemObject.addProperty("close_out", attrObject.get("value").getAsString());
				}
				
				if(codeElement.getAsString().equals("on_sale")) {
					itemObject.addProperty("on_sale", attrObject.get("value").getAsString());
				}
				
				if(codeElement.getAsString().equals("special_price")) {
					
					itemObject.addProperty("special_price", attrObject.get("value").getAsDouble());
				}
				if(codeElement.getAsString().equals("bulk")){
					itemObject.addProperty("bulk",  attrObject.get("value").getAsString());
				}		
				
			}
			
			for(JsonElement pricesElement:tierPrices) {
				
				JsonObject obj = pricesElement.getAsJsonObject();
				
				JsonElement customerGroupElement = obj.get("customer_group_id");
			
				if(customerGroupElement.getAsString().equals(groupId)) {
					
					itemObject.addProperty("tierprice", obj.get("value").getAsDouble());
				}
			}
		}
		
		JsonElement arrayElement = json.fromJson(itemArray, JsonElement.class);
		object.add("items", arrayElement);
		object.addProperty("recsPerPage", recsPerPage);
		JsonElement totalElement = object.get("total_count");
		int total = totalElement.getAsInt();
		double dPage;
		if(total > 28) {
		 dPage = ((double)total / (double)recsPerPage);
		
		dPage = Math.ceil(dPage);
		}else {
			dPage = 0;
		}
		object.addProperty("pageTotal", (int)dPage);
		JsonElement catListElement = json.fromJson(catList, JsonElement.class);
		object.add("categorylist", catListElement);
		
		return object.toString();
	}
	
	private String getImagePath(String productSku,SlingHttpServletRequest request) throws Exception{
		
		Session session = request.getResourceResolver().adaptTo(Session.class);
		QueryManager queryManager = session.getWorkspace().getQueryManager();
		String path="";
		String imgPath="";
		
		Resource res = request.getResource();
		ResourceResolver resourceResolver = request.getResourceResolver();
		
		
		String sqlStatement="SELECT * FROM [nt:unstructured] AS node\n" + 
	    		"WHERE ISDESCENDANTNODE(node, \"/content/dam/FrontierImages/product/"+ productSku+"\")"+
	    		"ORDER BY node.title"; 
	    			
		
		Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");	   		   
	    QueryResult result = query.execute();	  
	    NodeIterator nodeIter = result.getNodes();
		  
	    while ( nodeIter.hasNext() ) {
	    	Node node = nodeIter.nextNode();
	        path = node.getPath();
	       
	        res = resourceResolver.getResource(path);	        
	        String name = node.getName();
	        if(name.equals("jcr:content")) {
	        
	        	ValueMap properties = res.adaptTo(ValueMap.class);
	        	
	        	
	        		String relativePath = properties.get("dam:relativePath",(String)null);
		        	log.debug("relative path is "+relativePath);
		        	if(relativePath != null) {
		        		imgPath="";
		        		boolean bFind = Pattern.compile(Pattern.quote("1_"), Pattern.CASE_INSENSITIVE).matcher(relativePath).find();
		        		if(bFind) {
		        				        			
		        			imgPath = "/content/dam/"+ relativePath;
		        			
		        			 return imgPath;
		        		}
		        	}
	        	}
	        	
	        	
	        }    
	   		 return "";   
	}

	private String getCustomerGroupId(String jsonObject) throws Exception{
		Gson json = new Gson();
		JsonElement element = json.fromJson(jsonObject, JsonElement.class);
		
		
		JsonObject object = element.getAsJsonObject();
		JsonObject customerObject = object.getAsJsonObject("frontier_customer");
		
		String groupId = customerObject.get("group_id").getAsString();
		
		return groupId;
		
	}
	
	
}
