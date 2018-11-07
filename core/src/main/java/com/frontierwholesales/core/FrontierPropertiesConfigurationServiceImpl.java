package com.frontierwholesales.core;


import java.util.Dictionary;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        label = "Frontier properties configurator",
        metatype = true,
        immediate = true,
        policy = ConfigurationPolicy.OPTIONAL)
@Service(value = FrontierPropertiesConfigurationService.class)
public class FrontierPropertiesConfigurationServiceImpl implements FrontierPropertiesConfigurationService {
	private Logger log = LoggerFactory.getLogger(FrontierPropertiesConfigurationServiceImpl.class);

	private static final String DEFAULT_HOTJAR_ID = "";
	
	@Property( label="HotJar ID" ) 
    private static final String HOTJAR_ID_KEY = "frontier.hotjar.id";
	
	private String hotjarId;


	protected void activate( ComponentContext context ) {
		Dictionary<?,?> properties = context.getProperties();
		hotjarId = PropertiesUtil.toString(properties.get(HOTJAR_ID_KEY), DEFAULT_HOTJAR_ID);
		log.info("Initialize Frontier Properties Service");
		log.info("hotjarId : {}", hotjarId);
	}

	@Override
	public String getHotjarId() { return this.hotjarId; }

}
