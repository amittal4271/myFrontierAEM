package com.frontierwholesales.core.magento.services.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.MagentoCategory;

@Model(adaptables = SlingHttpServletRequest.class)
public class CategoriesModel extends BaseModel {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesModel.class);
	private MagentoCategory categories;
	@Override
	protected void init() {
		LOGGER.debug("CategoriesModel Start");
		try {
		String adminToken = getAdminToken();
		int categoryId = Integer.parseInt((String)slingHttpServletRequest.getAttribute("id"));
		categories = connector.getCategories(adminToken, categoryId);
		LOGGER.debug("CategoriesModel End");
		}catch(Exception anyEx) {
			LOGGER.error("Exception in CategoriesModel ",anyEx,anyEx.getMessage());
		}
	}
	
	public MagentoCategory getCategories() {
		return categories;
	}

}
