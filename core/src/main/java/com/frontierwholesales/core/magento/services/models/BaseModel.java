package com.frontierwholesales.core.magento.services.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.Cookie;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;
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
	@Inject
	private MagentoCommerceConnectorService config;
	 @PostConstruct
	  protected abstract void init();
	 
	 
	 
	 protected String getAdminToken(){
		
		  return config.getAppToken();
			
		
	 }
	 
	 public String getUserToken() {
		 Cookie cookie = (Cookie)slingHttpServletRequest.getCookie(FrontierWholesalesConstants.MAGENTO_USER_TOKEN);
		 this.userToken = cookie.getValue();
		
		 return userToken;
	 }
	 
	 public String getMagentoServer() {
		 return config.getServer();
	 }
}
