package com.frontierwholesales.core.magento.services;

public interface EmailConnectorService {

	String getHostName();
	 String getSmtpPort();
	 String getFromAddress();
	 String getToAddress();
	 String getSmtpUser();
	 String getEmailPwd();

}
