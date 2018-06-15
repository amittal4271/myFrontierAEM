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
public class AddressAdd {

	private Logger log = LoggerFactory.getLogger(AddressAdd.class);
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
	
	@Inject
	@Via("resource")
	@Default(values = "Create a New Address")
	public String addNewAddressHeader;

	@Inject
	@Via("resource")
	@Default(values = "Company")
	public String companyLabel;

	@Inject
	@Via("resource")
	@Default(values = "Full Name")
	public String fullNameLabel;

	@Inject
	@Via("resource")
	@Default(values = "Address Line 1")
	public String addressLine1Label;

	@Inject
	@Via("resource")
	@Default(values = "123 Example Street")
	public String addressLine1Hint;

	@Inject
	@Via("resource")
	@Default(values = "Address Line 2")
	public String addressLine2Label;

	@Inject
	@Via("resource")
	@Default(values = "Suite 55C")
	public String addressLine2Hint;

	@Inject
	@Via("resource")
	@Default(values = "City")
	public String cityLabel;

	@Inject
	@Via("resource")
	@Default(values = "State")
	public String stateLabel;

	@Inject
	@Via("resource")
	@Default(values = "Zip")
	public String zipLabel;

	@Inject
	@Via("resource")
	@Default(values = "Country")
	public String countryLabel;

	@Inject
	@Via("resource")
	@Default(values = "Building")
	public String buildingLabel;

	@Inject
	@Via("resource")
	@Default(values = "Phone")
	public String phoneLabel;

	@Inject
	@Via("resource")
	@Default(values = "Fax")
	public String faxLabel;

	@Inject
	@Via("resource")
	@Default(values = "Save")
	public String saveButtonText;
	
	@PostConstruct
	protected void activate() {
		
	}

}
