package com.frontierwholesales.core.magento.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = EmailConnectorService.class,configurationPolicy=ConfigurationPolicy.REQUIRE)
@Designate(ocd = EmailConnectorConfiguration.class,factory=true)
public class EmailConnector implements EmailConnectorService{

	
	    private EmailConnectorConfiguration config;
		
		
	    @Activate
	    public void activate(EmailConnectorConfiguration config) {
	    	
	    	this.config = config;
	       
	       
	    }

		public String getHostName() {
			
			return config.hostName();
		}

		
		public String getSmtpPort() {
			return config.smtpPort();
		}

		
		public String getFromAddress() {
			return config.fromAddress();
		}

		

		public String getToAddress() {
			return config.toAddress();
		}

		

		public String getSmtpUser() {
			return config.smtpUser();
		}

		
		public String getEmailPwd() {
			return config.emailP();
		}

		
		
	   
	    
	   

}
