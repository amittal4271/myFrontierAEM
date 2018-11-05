package com.frontierwholesales.core.magento.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "FrontierWholesaleMagentoCommerceConnectorService Configuration", description = "FrontierWholesaleMagentoCommerceConnectorService Configuration")
public @interface MagentoCommerceConnectorConfiguration {

	@AttributeDefinition(name = "App Token", description = "Magento Application Token")
	String appToken();
	
	@AttributeDefinition(name = "Magento Servier Name", description = "Magento Server URL")
	String serverName();
	
}
