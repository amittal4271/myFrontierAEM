package com.frontierwholesales.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.search.MagentoSearch;
import com.frontierwholesales.core.beans.search.MagentoSearchCondition;
import com.frontierwholesales.core.beans.search.MagentoSearchFilterGroup;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SlingServlet(paths = "/bin/product/search", methods = "GET")
public class MagentoProductSearchServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932570491641217502L;
	
	private static final Logger log = LoggerFactory.getLogger( MagentoProductSearchServlet.class );
	
	private static final String QUERY_PARAM = "q";
	private static final String PAGESIZE_PARAM = "searchCriteria[pageSize]";
	private static final String PAGE_PARAM = "searchCriteria[currentPage]";
	
	private static final int DEFAULT_CURRENT_PAGE = 1;
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	@Reference
	private FrontierWholesalesMagentoCommerceConnector connector;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = getPageRequestParameter(request);
		int pageSize = getPageSizeRequestParameter(request);
		
		long startTime = System.currentTimeMillis(); 
		log.debug("Processing search request: query={}; current_page={}; page_size={}", new Object[] {currentPage, pageSize});
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter writer = response.getWriter();
		
		try {
			
			//if(searchParam != null && searchParam.length()>0) {
				//search(writer, searchParam, currentPage, pageSize);
				String adminToken = connector.getAdminToken();
				String productList = connector.getProducts(adminToken, request.getQueryString());
				
				try {
					productList = parseJsonObject(productList, pageSize, currentPage, request);
				}catch(Exception e) {
				}
				
				writer.println(productList);
				writer.flush();
			
			//} 
		
		} catch (FrontierWholesalesBusinessException e) {
			log.error("Unable to process Magento search.", e);
		} finally {
			
			if(log.isDebugEnabled()) {
				log.debug("Search request completed in {} ms", System.currentTimeMillis()-startTime);
			}
		}
	}
	
	public String getUserToken(SlingHttpServletRequest request) {
		 Cookie cookie = (Cookie)request.getCookie("CustomerData");
		 return cookie.getValue();
	 }
	
	private JsonObject extractJsonObject(String jsonObject)throws Exception {
		 Gson gson = new Gson();
		 JsonElement element = gson.fromJson(jsonObject, JsonElement.class);
		 JsonObject object = element.getAsJsonObject();
		 return object;
	}

	private String parseJsonObject(String productList,int recsPerPage,int currentPage,
			SlingHttpServletRequest request) throws Exception{
		
		Gson json = new Gson();
		JsonElement element = json.fromJson(productList, JsonElement.class);
		
		
		JsonObject object = element.getAsJsonObject();
		JsonArray itemArray = object.getAsJsonArray("items");
		
		String customerGroupId = null;
		
		try {
			String customerToken = getUserToken(request);
			JsonObject tokenObj = extractJsonObject(customerToken);
			String token = tokenObj.get("token").getAsString();
			String customer = connector.getCustomer(token);
			
			JsonObject customerObject = extractJsonObject(customer);
			customerGroupId = customerObject.get("group_id").getAsString();
		} catch(Exception e) {
			log.error("Issue getting customer group_id", e);
		}
		
		DecimalFormat priceFormat=new DecimalFormat("#0.00");
		for(int i=0;i<itemArray.size();i++) {
			
			JsonElement itemElement = itemArray.get(i);
			JsonObject itemObject = itemElement.getAsJsonObject();
			//JsonElement priceElement = itemObject.get("price");
			//itemObject.addProperty("formattedPrice", "$"+priceFormat.format(priceElement.getAsDouble()));
			JsonElement skuElement = itemObject.get("sku");
			
			itemObject.addProperty("imgPath", getImagePath(skuElement.getAsString(),request));
			JsonArray attributesArray = itemObject.getAsJsonArray("custom_attributes");
			
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
						
				
			}
			
			if(customerGroupId != null) {
				JsonArray tierPriceArr = itemObject.getAsJsonArray("tier_prices");
				if(tierPriceArr != null)
				for(JsonElement tierElem : tierPriceArr) {
					JsonObject tierObject = tierElem.getAsJsonObject();
					JsonElement groupElement = tierObject.get("customer_group_id");
				
					if(groupElement.getAsString().equals(customerGroupId)) {
						itemObject.addProperty("tierprice", tierObject.get("value").getAsDouble());
					}
				}
			}
			
			itemObject.remove("tier_prices");
		}
		
//		JsonElement arrayElement = json.fromJson(itemArray, JsonElement.class);
//		object.add("items", arrayElement);
		object.addProperty("recsPerPage", recsPerPage);
		
		String searchTerm = request.getParameter("searchCriteria[filter_groups][0][filters][0][value]");
		if(searchTerm != null) {
			searchTerm = searchTerm.replaceAll("%", "");
			object.addProperty("searchTerm", searchTerm);
		}
		
		
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
		//object.add("categories", catElement.getAsJsonObject());
		return object.toString();
	}
	

	private String getImagePath(String productSku,SlingHttpServletRequest request) throws Exception{
		
		Session session = request.getResourceResolver().adaptTo(Session.class);
		QueryManager queryManager = session.getWorkspace().getQueryManager();
		String path="";
		String imgPath="";
		//String nodeTitle="";
		Resource res = request.getResource();
		ResourceResolver resourceResolver = request.getResourceResolver();
		
		
		String sqlStatement="SELECT * FROM [nt:unstructured] AS node\n" + 
	    		"WHERE ISDESCENDANTNODE(node, \"/content/dam/FrontierImages/product/"+ productSku+"\")"; 
	    			
		
		Query query = queryManager.createQuery(sqlStatement,"JCR-SQL2");	   		   
	    QueryResult result = query.execute();	  
	    NodeIterator nodeIter = result.getNodes();
		   log.debug("before loop");
	    while ( nodeIter.hasNext() ) {
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
		        		log.debug("image name "+relativePath);
		        		imgPath = "/content/dam/"+ relativePath;
		        	}
	        	}
	        	
	        	
	        }
           
	       
		    		

	    
	    return imgPath;		    
	}
	

	private void search(PrintWriter writer, String text, int currentPage, int limit) throws FrontierWholesalesBusinessException {
		
		MagentoSearch query = createQuery(text);
		
		if(currentPage > 0) {
			query.setCurrentPage(currentPage);
		} else {
			query.setCurrentPage(DEFAULT_CURRENT_PAGE);
		}
		
		if(limit > 0) {
			query.setPageSize(limit);
		} else {
			query.setPageSize(DEFAULT_PAGE_SIZE);
		}
		
		String adminToken = connector.getAdminToken();
		String productList = connector.getProducts(adminToken, query);
		log.debug("Magento product search returned: {}", productList);
		
		writer.println(productList);
		writer.flush();
	}
	
	private MagentoSearch createQuery(String text) {
		MagentoSearch search = new MagentoSearch();
		String searchTerm = "%" + text + "%";
		
		MagentoSearchFilterGroup filter = new MagentoSearchFilterGroup();
		filter.addFilterCondition(new MagentoSearchCondition("description", searchTerm, "like"));
		filter.addFilterCondition(new MagentoSearchCondition("name", searchTerm, "like"));
		search.addFilter(filter);
		
		return search;
	}
	
	private int getPageRequestParameter(SlingHttpServletRequest request) {
		return getNumRequestParameter(request, PAGE_PARAM);
	}
	
	private int getPageSizeRequestParameter(SlingHttpServletRequest request) {
		return getNumRequestParameter(request, PAGESIZE_PARAM);
	}
	
	private int getNumRequestParameter(SlingHttpServletRequest request, String name) {
		int num = 0;
		String p = getRequestParameter(request, name);
		if(p != null) {
			try {
				num = Integer.parseInt(p);
			} catch(NumberFormatException nfe) {}
		}
		return num;
	}
	
	private String getRequestParameter(SlingHttpServletRequest request, String name) {
		String param = request.getParameter(name) == null ? null : request.getParameter(name).trim();
		return param;
	}

}
