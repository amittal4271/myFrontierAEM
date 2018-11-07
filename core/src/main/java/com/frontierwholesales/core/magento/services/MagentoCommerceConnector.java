package com.frontierwholesales.core.magento.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = MagentoCommerceConnectorService.class,
           configurationPolicy = ConfigurationPolicy.REQUIRE
           )
@Designate(ocd = MagentoCommerceConnectorConfiguration.class,factory=true)


public class MagentoCommerceConnector implements MagentoCommerceConnectorService{

	private MagentoCommerceConnectorConfiguration config;
	
	 
    @Activate
    protected void activate(MagentoCommerceConnectorConfiguration config) {
    	
    	this.config = config;
    	
       
    }
    @Override
   	public  String getAppToken() {
   		
   		return config.appToken();
   	}

      @Override
   	public  String getServer() {
   		
           return config.serverName();
       }
   	

}
