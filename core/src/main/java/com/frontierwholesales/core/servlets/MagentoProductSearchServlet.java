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
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
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
	
	private static final String PAGESIZE_PARAM = "searchCriteria[pageSize]";
	private static final String PAGE_PARAM = "searchCriteria[currentPage]";
	
	//TODO, handle default pagesize in query if not present
	private static final int DEFAULT_CURRENT_PAGE = 1;
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	@Reference
	private FrontierWholesalesMagentoCommerceConnector connector;
	private FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		final String authorization = request.getHeader("Authorization");
		String groupId ="";
		
		int currentPage = getPageRequestParameter(request);
		int pageSize = getPageSizeRequestParameter(request);
		
		long startTime = System.currentTimeMillis(); 
		log.debug("Processing search request: query={}; current_page={}; page_size={}", new Object[] {currentPage, pageSize});
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter writer = response.getWriter();
		
		if(authorization != null) {		
			try {
			groupId = utils.getCustomerDetailsByParameter("group_id", authorization);
			} catch(Exception e) {
				log.error("Could not get customer group_id", e);
			}
		}
		
		try {
			String adminToken = connector.getAdminToken();
			String productList = connector.getProducts(adminToken, request.getQueryString());
			
			try {
				productList = utils.parseJsonObject(productList, pageSize, currentPage, request, groupId);
			}catch(Exception e) {
			}
			
			writer.println(productList);
			writer.flush();
		} catch (FrontierWholesalesBusinessException e) {
			log.error("Unable to process Magento search.", e);
		} finally {
			if(log.isDebugEnabled()) {
				log.debug("Search request completed in {} ms", System.currentTimeMillis()-startTime);
			}
		}
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
