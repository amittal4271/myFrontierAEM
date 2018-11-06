package com.frontierwholesales.core.magento.services.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class })
public class MagentoInfo extends BaseModel {
	private static final Logger LOGGER = LoggerFactory.getLogger(MagentoInfo.class);

	@PostConstruct
	protected void init() {
			LOGGER.debug("MagentoInfo Model...");
	}

}
