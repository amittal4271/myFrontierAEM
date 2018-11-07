package com.frontierwholesales.core.magento.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Email Connector Configuration", description = "Email Service Configuration")

public @interface EmailConnectorConfiguration {
	 @AttributeDefinition(name = "Email Host Name", description = "Email Host Name Configuration value")
	    String hostName();
	 
	 @AttributeDefinition(name = "Email from Address", description = "Email from Address Configuration value")
	    String fromAddress();
	 
	 @AttributeDefinition(name = "Email To Address", description = "Email To Address Configuration value")
	    String toAddress();
	 
	 @AttributeDefinition(name = "SMTP Port", description = "SMTP Port Configuration value")
	    String smtpPort();
	 
	 @AttributeDefinition(name = "SMTP User", description = "SMTP User Configuration value")
	    String smtpUser();
	 
	 @AttributeDefinition(name = "Email SMTP Password", description = "Email SMTP Password Configuration value")
	    String emailP();
}
