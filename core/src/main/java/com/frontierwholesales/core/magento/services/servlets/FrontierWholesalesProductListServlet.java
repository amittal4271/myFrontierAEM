package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.FrontierWholesalesProductSearch;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesErrorCode;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Component(immediate = true,service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=FrontierWholesales Product List Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
       "sling.servlet.selectors=data",
       "sling.servlet.paths=/services/productlist",
        "sling.servlet.extensions=html"      
        
})


public class FrontierWholesalesProductListServlet extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesProductListServlet.class);
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	private FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
	
	
	
	private MagentoCommerceConnectorService config;
	@Reference
	public void activate(MagentoCommerceConnectorService config) {
		
		this.config = config;
	}
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("doGet FrontierWholesalesProductListServlet Start here ");
		
		try {
			final String authorization = request.getHeader("Authorization");
			
			String groupId ="";
			String adminToken =this.config.getAppToken();	
			String server = this.config.getServer();
			commerceConnector.setServer(server);
		   
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
				groupId = utils.getCustomerDetailsByParameter("group_id", authorization,server);
			}
			
			
			String productList = commerceConnector.getProducts(adminToken, search);
			String catList = commerceConnector.getParentChildrenCategories(adminToken, categoryId);
			catList = parseCatListObject(catList);
			response.setContentType("text/html;charset=UTF-8;");
			response.setCharacterEncoding("UTF-8");
			
			String jsonResponse = utils.parseJsonObject(productList,noOfRecsPerPage,currentPage,request,groupId);
			
			jsonResponse = utils.addCategoryListToJson(jsonResponse, catList);
			
			Cookie cookie = FrontierWholesalesUtils.getCookie(request,"CustomerData");
			if(cookie != null) {
				log.debug("cookie is available");
				String cookieValue = cookie.getValue();
				String authToken = FrontierWholesalesUtils.getIdFromObject(cookieValue, "token");
				jsonResponse = addUserTokenToObject(jsonResponse,"authToken",authToken);
				log.debug("auth token is available");
			}
			
			ServletOutputStream stream = response.getOutputStream();
			log.debug(" FrontierWholesalesProductListServlet operations End ");
			stream.write(jsonResponse.getBytes("UTF-8"));
			
		}
		catch(Exception anyEx) {
			log.error("Error in productList "+anyEx.getMessage());
			response.getOutputStream().println("Error "+anyEx.getMessage());
		}
		log.debug("doGet FrontierWholesalesProductListServlet End here ");
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("doPost FrontierWholesalesProductListServlet Start here ");
		try {
			
			String adminToken = this.config.getAppToken();
			commerceConnector.setServer(this.config.getServer());
			String jsonData = request.getParameter("wishlist");
			String wishList = commerceConnector.addItemToWishList(adminToken, jsonData);
			JsonObject object = new JsonObject();
			if(wishList != null) {
			
				object.addProperty("WishList", "Added To WishList");
			}
			response.getOutputStream().println(object.toString());
		}catch(FrontierWholesalesBusinessException businessEx) {
			log.error("Error in productList post method "+businessEx.getMessage());
			response.getOutputStream().println("Error "+businessEx.getMessage());
		}catch(Exception anyEx) {
			log.error("Error in productList post method "+anyEx.getMessage());
			response.getOutputStream().println("Error");
		}
		
		log.debug("doPost FrontierWholesalesProductListServlet End here ");
	}
	
	private String addUserTokenToObject(String object,String key,String value) throws FrontierWholesalesBusinessException{
			log.debug("addUserTokenToObject Start");
			JsonParser parser =  new JsonParser();
			JsonObject jsonObject;
			try {
				jsonObject =parser.parse(object).getAsJsonObject();			
				jsonObject.addProperty(key, value);
				
			}catch(Exception anyEx) {
				log.debug("Error in constructAPIMethod "+anyEx.getMessage());
	   	 		throw new FrontierWholesalesBusinessException("ServiceError", FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
			}
			log.debug("addUserTokenToObject End ");
			return jsonObject.toString();
	}
	
	private String parseCatListObject(String catList) throws FrontierWholesalesBusinessException{
		log.debug("parseCatListObject start");
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(catList).getAsJsonObject();
		try {
			JsonArray parentArray = object.get("parent").getAsJsonArray();
			JsonArray childArray = object.get("children").getAsJsonArray();
			int count=0;
			StringBuilder parentNames= new StringBuilder();
			log.debug("before array");
			for(JsonElement element:parentArray) {
				log.debug("inside array");
				
				if(count > 0) {
					JsonObject obj = element.getAsJsonObject();
					String name = obj.get("name").getAsString();
					name = name.toLowerCase();
					parentNames.append("/").append(name.replaceAll(" ", "-"));
					log.debug("name is "+name);
					obj.addProperty("categoryname", name.replaceAll(" ", "-"));
					
					
				}
				
				count++;
			}
			log.debug("before if condition");
			if(parentNames.toString() != "") {
				log.debug("parentNames are "+parentNames.toString());
				object.addProperty("fullpath", parentNames.toString());
			}
			
			for(JsonElement element:childArray) {
			
			
				JsonObject obj = element.getAsJsonObject();
				String name = obj.get("name").getAsString();
				name = name.toLowerCase();
				
				obj.addProperty("childname", name.replaceAll(" ","-"));
				obj.addProperty("childpath", parentNames.toString());
				
				count++;
			}
		}catch(Exception anyEx) {
			log.debug("Error in parseCatListObject "+anyEx.getMessage());
   	 		throw new FrontierWholesalesBusinessException("ServiceError", FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
		}
		log.debug("parseCatListObject End");
		return object.toString();
	}
}
