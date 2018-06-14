package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@SlingServlet(label="FrontierWholesalesAdminServlet - Sling All Methods Servlet", 
description="FrontierWholesales Admin Servlet Sling All Methods Servlet.", 
paths={"/services/admintoken"}, methods={"GET","POST"})

public class FrontierWholesalesAdminServlet extends SlingAllMethodsServlet{

	public FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	 private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesUserRegistrationServlet.class);
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		JsonObject json = new JsonObject();
		try {
			
			String adminToken = connector.getAdminToken();
			json.addProperty("Token", adminToken);
			response.getOutputStream().print(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.addProperty("Error", e.getMessage());			
			log.error("Error getting admin token",e,e.getMessage());
			response.getOutputStream().print(json.toString());
		}
	}
}
