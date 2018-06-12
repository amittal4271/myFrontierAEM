package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.commerce.api.Product;
import com.adobe.cq.commerce.common.CommerceHelper;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.frontierwholesales.core.beans.FrontierWholesalesProductSearch;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@SlingServlet(label="FrontierWholesalesProductListServlet - Sling All Methods Servlet", 
description="FrontierWholesales Product List Servlet Sling All Methods Servlet.", 
paths={"/services/productlist"}, methods={"GET","POST"})

public class FrontierWholesalesProductListServlet extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesProductListServlet.class);
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("doGet FrontierWholesalesProductListServlet Start here");
		try {
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
			
			String adminToken = getTokenFromSession(request);
			//String categories = commerceConnector.getCategories(adminToken, categoryId);
			String productList = commerceConnector.getProducts(adminToken, search);
			
			response.getOutputStream().println(parseJsonObject(productList,noOfRecsPerPage,currentPage));
		}catch(Exception anyEx) {
			log.error("Error in productList "+anyEx.getMessage());
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
	
	private String parseJsonObject(String productList,int recsPerPage,int currentPage
			) throws Exception{
		
		Gson json = new Gson();
		JsonElement element = json.fromJson(productList, JsonElement.class);
		//JsonElement catElement = json.fromJson(categories, JsonElement.class);
		
		JsonObject object = element.getAsJsonObject();
		JsonArray itemArray = object.getAsJsonArray("items");
		
		
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
		for(int i=0;i<itemArray.size();i++) {
			
			JsonElement itemElement = itemArray.get(i);
			JsonObject itemObject = itemElement.getAsJsonObject();
			JsonElement priceElement = itemObject.get("price");
			itemObject.addProperty("formattedPrice", "$"+priceFormat.format(priceElement.getAsDouble()));
			
			JsonArray attributesArray = itemObject.getAsJsonArray("custom_attributes");
			
			for(JsonElement attributesElement:attributesArray) {
				JsonObject attrObject = attributesElement.getAsJsonObject();
				JsonElement codeElement = attrObject.get("attribute_code");
				if(codeElement.getAsString().equals("image")) {
					itemObject.addProperty("imgPath", attrObject.get("value").getAsString());
				}
			}
		}
		
		JsonElement arrayElement = json.fromJson(itemArray, JsonElement.class);
		object.add("items", arrayElement);
		object.addProperty("recsPerPage", recsPerPage);
		JsonElement totalElement = object.get("total_count");
		int total = totalElement.getAsInt();
		
		double dPage = ((double)total / (double)recsPerPage);
		
		dPage = Math.ceil(dPage);
		
		object.addProperty("pageTotal", (int)dPage);
		//object.add("categories", catElement.getAsJsonObject());
		return object.toString();
	}
	
	
}
