package com.frontierwholesales.core.magento.services.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public abstract class BaseModel {

	@Inject
	public Resource resource;
	@Inject
	public SlingHttpServletRequest slingHttpServletRequest;
	protected String adminToken;
	protected String userToken;

	protected FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	protected ObjectMapper mapper = new ObjectMapper();
	
	 @PostConstruct
	  protected abstract void init();
	 
	 
	 
	 protected String getAdminToken(SlingHttpServletRequest request) throws Exception{
		 String adminToken = (String)slingHttpServletRequest.getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_ADMIN_TOKEN);
		 if(null == adminToken) {
		  adminToken = connector.getAdminToken();
			
		request.getSession().setAttribute(FrontierWholesalesConstants.MAGENTO_ADMIN_TOKEN, adminToken);
		 }
		 return adminToken;
	 }
	 
	 public String getUserToken() {
		 this.userToken = (String)slingHttpServletRequest.getSession().getAttribute(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
		
		 return userToken;
	 }
}
