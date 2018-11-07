package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;

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
	

	private MagentoCommerceConnectorService config;
	@Reference
	public void activate(MagentoCommerceConnectorService config) {
		
		this.config = config;
	}
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("doGet FrontierWholesalesBrandListServlet Start here ");
		try {
			
			
			String adminToken = this.config.getAppToken();
			commerceConnector.setServer(this.config.getServer());			
			String brandlist = commerceConnector.getBrands(adminToken);
			
			
			response.getOutputStream().println(brandlist);
		}catch(Exception anyEx) {
			log.error("Error in productList "+anyEx.getMessage());
			response.getOutputStream().println("Error");
		}
	}
}
