package com.frontierwholesales.core.models;

import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.MagentoCategory;
import com.frontierwholesales.core.data.cache.CacheManager;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.constants.FrontierWholesalesConstants;


@Model (adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class ProductListPage {

	private Logger log = LoggerFactory.getLogger(ProductListPage.class);
	private FrontierWholesalesMagentoCommerceConnector connector = new FrontierWholesalesMagentoCommerceConnector();
	
	private MagentoCategory categories;
	
	private LinkedHashMap<String,MagentoCategory> categorySeoPathMap = new LinkedHashMap<String, MagentoCategory>();
	
	private LinkedHashMap<String,String> categorySeoIdMap = new LinkedHashMap<String, String>();
	
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
	
	@PostConstruct
	protected void activate() {
		try {
			String adminToken =  connector.getAdminToken();
			
			this.categories =  (MagentoCategory)CacheManager.getCache(FrontierWholesalesConstants.MAGENTO_CATEGORIES);
			if(this.categories == null) {
				CacheManager.putCache(getAllCategories(adminToken,2),FrontierWholesalesConstants.MAGENTO_CATEGORIES);
				this.categories =  (MagentoCategory)CacheManager.getCache(FrontierWholesalesConstants.MAGENTO_CATEGORIES);
			}
			
			//TODO, could replace nested for loops with recursive function, for less code duplication
			//TODO, could cache the category map, for better performance
			for(MagentoCategory categoryLevel1 : categories.children_data) {
				categorySeoPathMap.put(categoryLevel1.getPathName(), categoryLevel1);
				categorySeoIdMap.put(String.valueOf(categoryLevel1.id), categoryLevel1.getPathName());
				
				if(categoryLevel1.children_data != null && categoryLevel1.children_data.length > 0) {
					
					for(MagentoCategory categoryLevel2 : categoryLevel1.children_data) {
						categorySeoPathMap.put(categoryLevel1.getPathName()+"/"+categoryLevel2.getPathName(), categoryLevel2);
						categorySeoIdMap.put(String.valueOf(categoryLevel2.id), categoryLevel1.getPathName()+"/"+categoryLevel2.getPathName());
						
						if(categoryLevel2.children_data != null && categoryLevel2.children_data.length > 0) {
							for(MagentoCategory categoryLevel3 : categoryLevel2.children_data) {
								categorySeoPathMap.put(categoryLevel1.getPathName()+"/"+categoryLevel2.getPathName()+"/"+categoryLevel3.getPathName(), categoryLevel3);
								categorySeoIdMap.put(String.valueOf(categoryLevel3.id), categoryLevel1.getPathName()+"/"+categoryLevel2.getPathName()+"/"+categoryLevel3.getPathName());
								
								if(categoryLevel3.children_data != null && categoryLevel3.children_data.length > 0) {
									for(MagentoCategory categoryLevel4 : categoryLevel3.children_data) {
										categorySeoPathMap.put(categoryLevel1.getPathName()+"/"+categoryLevel2.getPathName()+"/"+categoryLevel3.getPathName()+"/"+categoryLevel4.getPathName(), categoryLevel4);
										categorySeoIdMap.put(String.valueOf(categoryLevel4.id), categoryLevel1.getPathName()+"/"+categoryLevel2.getPathName()+"/"+categoryLevel3.getPathName()+"/"+categoryLevel4.getPathName());
									}
								}
							}
						}
					}
				}
			}
			
			
		}
		catch(Exception e) {
			log.error("ERROR activating productlist page model", e);
		}
	}
	
	public MagentoCategory getPageCategory() {
		return categorySeoPathMap.get(request.getRequestPathInfo().getSuffix().substring(1));
	}
	
	public String getPageCategoryById(String id) {
		return categorySeoIdMap.get(id);
	}
	
	private MagentoCategory getAllCategories(String adminToken,int categoryId) throws Exception{
		MagentoCategory categories = connector.getCategories(adminToken, categoryId);
		return categories;
	}

}
