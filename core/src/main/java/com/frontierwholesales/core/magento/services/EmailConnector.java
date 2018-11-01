package com.frontierwholesales.core.magento.services;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.oak.spi.security.ConfigurationParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
		immediate = true,
        label = "Frontier Wholesale Email Connector",
        configurationFactory = true,
        metatype = true,
        policy = ConfigurationPolicy.REQUIRE
)
@Service(EmailConnector.class)
@Properties(value = {
    @Property(name = "service.description", value = "Frontier Wholesale Email Connector"),
    @Property(name = "service.vendor", value = "Frontier Wholesale"),
})
public class EmailConnector {

	
	 
	 private static String hostName;
	 private static String smtpPort;
	 private static String fromAddress;
	 private static String toAddress;
	 private static String smtpUser;
	 private static String emailPwd;
	 
	 
	 @Property(
	    		
	            label = "SMTP Server Host name",
	            description = "SMTP Server Host name",
	            value = "hostname"
	    )
	    public static String HOST_NAME ="hostName";
	 
	  @Property(
	    		
	            label = "From Address",
	            description = "Email From Address",
	            value = "no-reply@frontiercoop.com"
	    )
	    public static final String FROM_ADDRESS = "fromAddress";
	    

	    @Property(
	    		
	            label = "To Address",
	            description = "Email To Address",
	            value = "customercare@frontiercoop.com"
	    )
	    public static final String TO_ADDRESS = "toAddress";
	    
	    @Property(
	    		
	            label = "SMTP Server Port",
	            description = "SMTP Server Port",
	            value = "465"
	    )
	    public static final String SMTP_PORT = "smtpPort";
	    
	    @Property(
	    		
	            label = "SMTP Email User",
	            description = "SMTP Email User",
	            value = "smtpuser@frontiercoop.com"
	    )
	    public static final String SMTP_USER = "smtpUser";
	    
	    @Property(
	    		
	            label = "SMTP Email Password",
	            description = "SMTP Email Password",
	            value = ""
	    )
	    public static final String EMAIL_PWD = "email_pwd";
	    
	    @Activate
	    protected void activate(Map<String, Object> properties) {
	    	
	        ConfigurationParameters cfg = ConfigurationParameters.of(properties);
	        
	        EmailConnector.hostName = cfg.getConfigValue(HOST_NAME, "");
	        
	        EmailConnector.fromAddress = cfg.getConfigValue(FROM_ADDRESS, "");
	       
	        EmailConnector.toAddress = cfg.getConfigValue(TO_ADDRESS, "");
	        
	        EmailConnector.smtpPort = cfg.getConfigValue(SMTP_PORT, "");
	        
	        EmailConnector.smtpUser = cfg.getConfigValue(SMTP_USER, "");
	        
	        EmailConnector.emailPwd = cfg.getConfigValue(EMAIL_PWD, "");
	       
	       
	    }

		public static String getHostName() {
			return hostName;
		}

		
		public static String getSmtpPort() {
			return smtpPort;
		}

		

		public static String getToAddress() {
			return toAddress;
		}

		

		public static String getSmtpUser() {
			return smtpUser;
		}

		

		public static String getPassword() {
			return emailPwd;
		}

		

		public static String getFromAddress() {
			return fromAddress;
		}
		
		
	    
		
		
	   
	    
	   

}
