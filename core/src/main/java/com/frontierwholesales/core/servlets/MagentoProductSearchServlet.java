package com.frontierwholesales.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesErrorCode;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Component(immediate = true,service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=MagentoProduct Search Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
       "sling.servlet.selectors=data",
       "sling.servlet.paths=/services/productsearch",
        "sling.servlet.extensions=json"      
        
})
public class MagentoProductSearchServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932570491641217502L;
	
	private static final Logger log = LoggerFactory.getLogger( MagentoProductSearchServlet.class );
	
	private static final String PAGESIZE_PARAM = "searchCriteria[pageSize]";
	private static final String PAGE_PARAM = "searchCriteria[currentPage]";
	
	
	private FrontierWholesalesMagentoCommerceConnector connector= new FrontierWholesalesMagentoCommerceConnector();
	private FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
	
	private MagentoCommerceConnectorService config;
	@Reference
	public void activate(MagentoCommerceConnectorService config) {
		
		this.config = config;
	}
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		final String authorization = request.getHeader("Authorization");
		String groupId ="";
		
		int currentPage = getPageRequestParameter(request);
		int pageSize = getPageSizeRequestParameter(request);
		Object[] obj = new Object[2];
		obj[0]=currentPage;
		obj[1]=pageSize;
		
		long startTime = System.currentTimeMillis(); 
		log.debug("Processing search request: query={}; current_page={}; page_size={}", obj);
		
		response.setContentType(FrontierWholesalesConstants.CONTENT_TYPE_JSON);
		response.setCharacterEncoding(FrontierWholesalesConstants.UTF8);
		
		PrintWriter writer = response.getWriter();
		
		String adminToken = this.config.getAppToken();
		String server = this.config.getServer();
		connector.setServer(server);
		
		if(authorization != null) {		
			try {
			groupId = utils.getCustomerDetailsByParameter("group_id", authorization,server);
			} catch(Exception e) {
				log.error("Could not get customer group_id", e);
			}
		}
		
		try {
			
			String productList = getProducts(connector,adminToken,request,pageSize,currentPage,groupId);		
			
			writer.println(productList);
			writer.flush();
		}catch (FrontierWholesalesBusinessException e) {
			log.error("Unable to process Magento search.", e);
			writer.println("MagentoSearch Error");
			writer.flush();
		} catch (Exception e) {
			log.error("Unable to process Magento search.", e);
			writer.println("Error in MagentoSearch");
			writer.flush();
		}finally {
			if(log.isDebugEnabled()) {
				log.debug("Search request completed in {} ms", System.currentTimeMillis()-startTime);
			}
		}
	}
	
	private String getProducts(FrontierWholesalesMagentoCommerceConnector connector,String adminToken,SlingHttpServletRequest request,int pageSize,int currentPage,String groupId) throws FrontierWholesalesBusinessException{
		String productList = null;
		try {
			productList = connector.getProducts(adminToken, request.getQueryString());
			productList = utils.parseJsonObject(productList, pageSize, currentPage, request, groupId);
			Cookie cookie = FrontierWholesalesUtils.getCookie(request,"CustomerData");
			if(cookie != null) {
				
				String cookieValue = cookie.getValue();
				String authToken = FrontierWholesalesUtils.getIdFromObject(cookieValue, "token");
				productList = addUserTokenToObject(productList,"authToken",authToken);
				
			}
		}catch(Exception e) {
			throw new FrontierWholesalesBusinessException("Service Error ",FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
		}
		return productList;
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
			} catch(NumberFormatException nfe) {
				log.debug("Error in getNumRequestParameter",nfe);
			}
		}
		return num;
	}
	
	private String getRequestParameter(SlingHttpServletRequest request, String name) {
		String param = request.getParameter(name) == null ? null : request.getParameter(name).trim();
		return param;
	}
	
	private String addUserTokenToObject(String object,String key,String value) throws Exception{
		log.debug("addUserTokenToObject Start");
		JsonParser parser =  new JsonParser();
		JsonObject jsonObject =parser.parse(object).getAsJsonObject();
		
		jsonObject.addProperty(key, value);
	
		log.debug("addUserTokenToObject End ");
		return jsonObject.toString();
	}

}
