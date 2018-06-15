package com.frontierwholesales.core.models.myaccount;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model (adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class ProfileEditSubscriptions {

	private Logger log = LoggerFactory.getLogger(ProfileEditSubscriptions.class);
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
	
	@Inject
	@Via("resource")
	@Default(values = "Subscriptions")
	public String subscriptionsHeader;

	@Inject
	@Via("resource")
	@Default(values = "Email - Email")
	public String emailCheckboxLabel;

	@Inject
	@Via("resource")
	@Default(values = "Catalog - Print")
	public String printCheckboxLabel;

	@Inject
	@Via("resource")
	@Default(values = "Update")
	public String updateButtonText;

	@PostConstruct
	protected void activate() {
		
	}

}
