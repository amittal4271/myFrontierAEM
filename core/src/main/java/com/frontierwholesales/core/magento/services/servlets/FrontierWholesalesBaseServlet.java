package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.FrontierWholesalesUserRegistration;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@SlingServlet(label="FrontierWholesalesBaseServlet - Sling All Methods Servlet", 
description="FrontierWholesalesBaseServlet - It contains retrieval of tokens and emailid. Stored in Session", 
 methods={"GET","POST"})
public class FrontierWholesalesBaseServlet extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesBaseServlet.class);
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected FrontierWholesalesMagentoCommerceConnector commerceConnector = new FrontierWholesalesMagentoCommerceConnector();
	
	protected String countriesWithRegions;
	protected String adminToken;
	@Override
	public void init() {
		 mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	}
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("BaseServlet Start");
		try {
			
			 adminToken = (String)getTokenFromSession(request); 
			log.debug("Base Servlet after admin token");
		} catch (Exception e) {
			log.error("Exception occurred in doGet method "+e.getMessage(),e);
			
		}
		log.debug("BaseServlet End");
			
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
	
}
