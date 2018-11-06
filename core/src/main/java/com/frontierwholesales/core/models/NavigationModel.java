package com.frontierwholesales.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.designer.Style;
import com.frontierwholesales.core.beans.FrontierWholesalePages;
import com.frontierwholesales.core.beans.MagentoCategory;
import com.frontierwholesales.core.data.cache.CacheManager;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.Cookie;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class },defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	public class NavigationModel {
		private List<FrontierWholesalePages> frontierWholesalePages;
		private static final Logger LOGGER = LoggerFactory.getLogger(NavigationModel.class);
		private FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
		
		private MagentoCategory categories;
		
		 @Self
	    private SlingHttpServletRequest request;

	    @SlingObject
	    private ResourceResolver resourceResolver;

	    @ScriptVariable
	    private Page currentPage;
	    
	    @ScriptVariable
	    private ValueMap properties;
	    
	    @ScriptVariable
	    private Style currentStyle;

		@Inject
		private MagentoCommerceConnectorService config;
		
		@PostConstruct
		public void init() throws FrontierWholesalesBusinessException {
			LOGGER.debug("activate method of navigation Start");
			
			try {				
				
	            Page root = currentPage.getAbsoluteParent(2);
	           
				FrontierWholesalePages frontierRoot = new FrontierWholesalePages(root);
				
				this.frontierWholesalePages =  (List<FrontierWholesalePages>)CacheManager.getCache(FrontierWholesalesConstants.AEM_CHILDREN_PAGES);
				
				if(this.frontierWholesalePages == null) {
					CacheManager.putCache(this.getChildren(frontierRoot, 0),FrontierWholesalesConstants.AEM_CHILDREN_PAGES);
					
					this.frontierWholesalePages =  (List<FrontierWholesalePages>)CacheManager.getCache(FrontierWholesalesConstants.AEM_CHILDREN_PAGES);
					
				}
				String adminToken =  this.config.getAppToken();
				
				
				this.categories =  (MagentoCategory)CacheManager.getCache(FrontierWholesalesConstants.MAGENTO_CATEGORIES);
				if(this.categories == null) {
					CacheManager.putCache(getAllCategories(adminToken,2),FrontierWholesalesConstants.MAGENTO_CATEGORIES);
					this.categories =  (MagentoCategory)CacheManager.getCache(FrontierWholesalesConstants.MAGENTO_CATEGORIES);
				}
			}
			catch(Exception anyEx) {
				LOGGER.error(anyEx.getMessage(), anyEx);
			}
			LOGGER.debug("activate method of navigation End");
		}

		private List<FrontierWholesalePages> getChildren(FrontierWholesalePages parent, int level) {
			ArrayList<FrontierWholesalePages> pages = new ArrayList<FrontierWholesalePages>();
			Iterator<Page> children = parent.getPage().listChildren();

			while (children.hasNext()) {
				Page page = (Page) children.next();
				
				FrontierWholesalePages frontierWholesalePage = new FrontierWholesalePages(page);
				String showNav = frontierWholesalePage.getShowInNav();				
				if (showNav != null && showNav.equals("true")) {
					
					frontierWholesalePage.setChildList(this.getChildren(frontierWholesalePage, level + 1));
					pages.add(frontierWholesalePage);
				}
			}

			return pages;
		}
		
		private MagentoCategory getAllCategories(String adminToken,int categoryId) throws FrontierWholesalesBusinessException{
			MagentoCategory _categories = connector.getCategories(adminToken, categoryId, config.getServer());
			
			return _categories;
		}

		public List<FrontierWholesalePages> getFrontierWholesalePages() {
			return this.frontierWholesalePages;
		}
		
		public MagentoCategory getCategories() {
			return categories;
		}
}

