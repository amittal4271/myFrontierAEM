package com.frontierwholesales.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.search.MagentoSearch;
import com.frontierwholesales.core.beans.search.MagentoSearchCondition;
import com.frontierwholesales.core.beans.search.MagentoSearchFilterGroup;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;

@SlingServlet(paths = "/bin/product/search", methods = "GET")
public class MagentoProductSearchServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932570491641217502L;
	
	private static final Logger logger = LoggerFactory.getLogger( MagentoProductSearchServlet.class );
	
	private static final String QUERY_PARAM = "q";
	private static final String PAGESIZE_PARAM = "page_size";
	private static final String PAGE_PARAM = "current_page";
	
	private static final int DEFAULT_CURRENT_PAGE = 1;
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	@Reference
	private FrontierWholesalesMagentoCommerceConnector connector;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String searchParam = getRequestParameter(request, QUERY_PARAM);
		int currentPage = getPageRequestParameter(request);
		int pageSize = getPageSizeRequestParameter(request);
		
		long startTime = System.currentTimeMillis(); 
		logger.debug("Processing search request: query={}; current_page={}; page_size={}", new Object[] {searchParam, currentPage, pageSize});
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter writer = response.getWriter();
		
		try {
			
			if(searchParam != null && searchParam.length()>0) {
				search(writer, searchParam, currentPage, pageSize);
			} 
		
		} catch (FrontierWholesalesBusinessException e) {
			logger.error("Unable to process Magento search.", e);
		} finally {
			
			if(logger.isDebugEnabled()) {
				logger.debug("Search request completed in {} ms", System.currentTimeMillis()-startTime);
			}
		}
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
		logger.debug("Magento product search returned: {}", productList);
		
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
