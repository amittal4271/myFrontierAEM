package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.FrontierWholesalesProductSearch;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")

@Component(immediate = true,service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=FrontierWholesales Brand List Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
       "sling.servlet.selectors=data",
       "sling.servlet.paths=/services/brandlist",
        "sling.servlet.extensions=json"      
        
})


public class FrontierWholesalesBrandListServlet extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesBrandListServlet.class);
	private FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("doGet FrontierWholesalesBrandListServlet Start here ");
		try {
			
			
			String adminToken = commerceConnector.getAdminToken();
			//String categories = commerceConnector.getCategories(adminToken, categoryId);
			String brandlist = commerceConnector.getBrands(adminToken);
			
			
			response.getOutputStream().println(brandlist);
		}catch(Exception anyEx) {
			log.error("Error in productList "+anyEx.getMessage());
			response.getOutputStream().println("Error");
		}
	}
}
