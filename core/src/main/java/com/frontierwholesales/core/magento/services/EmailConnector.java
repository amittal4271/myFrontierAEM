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

	 private static final Logger log = LoggerFactory.getLogger(EmailConnector.class);
	 
	 private static String hostName;
	 private static String smtpPort;
	 private static String toAddress;
	 private static String smtpUser;
	 private static String password;
	 
	 
	 @Property(
	    		
	            label = "SMTP Server Host name",
	            description = "SMTP Server Host name",
	            value = "hostname"
	    )
	    public static String HOST_NAME ="hostName";
	    

	    @Property(
	    		
	            label = "To Address",
	            description = "Email To Addressr",
	            value = "no-reply@frontiercoop.com"
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
	    public static final String PASSWORD = "password";
	    
	    @Activate
	    protected void activate(Map<String, Object> properties) {
	    	
	        ConfigurationParameters cfg = ConfigurationParameters.of(properties);
	        
	        hostName = cfg.getConfigValue(HOST_NAME, "");
	       
	        toAddress = cfg.getConfigValue(TO_ADDRESS, "");
	        
	        smtpPort = cfg.getConfigValue(SMTP_PORT, "");
	        
	        smtpUser = cfg.getConfigValue(SMTP_USER, "");
	        
	        password = cfg.getConfigValue(PASSWORD, "");
	       
	       
	    }

		public String getHostName() {
			return hostName;
		}

		public void setHostName(String hostName) {
			EmailConnector.hostName = hostName;
		}

		public String getSmtpPort() {
			return smtpPort;
		}

		public void setSmtpPort(String smtpPort) {
			EmailConnector.smtpPort = smtpPort;
		}

		public String getToAddress() {
			return toAddress;
		}

		public void setFromAddress(String toAddress) {
			EmailConnector.toAddress = toAddress;
		}

		public String getSmtpUser() {
			return smtpUser;
		}

		public void setSmtpUser(String smtpUser) {
			EmailConnector.smtpUser = smtpUser;
		}

		public  String getPassword() {
			return password;
		}

		public  void setPassword(String password) {
			EmailConnector.password = password;
		}
	    
		
		
	   
	    
	   

}
