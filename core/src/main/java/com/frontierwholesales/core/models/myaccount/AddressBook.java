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
public class AddressBook {

	private Logger log = LoggerFactory.getLogger(AddressBook.class);
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
	
	@Inject
	@Via("resource")
	@Default(values = "Address Book")
	public String addressBookHeader;

	@Inject
	@Via("resource")
	@Default(values = "Add New Address")
	public String addNewAddressButtonText;
	
	@Inject
	@Via("resource")
	@Default(values = "/content/frontierwholesales/en/myaccount/addresses/add")
	public String addNewAddressButtonLink;

	@Inject
	@Via("resource")
	@Default(values = "Edit")
	public String editActionText;

	@Inject
	@Via("resource")
	@Default(values = "Delete")
	public String deleteActionText;
	
	@Inject
	@Via("resource")
	@Default(values = "Confirm Address Delete")
	public String deleteModalHeading;
	
	@Inject
	@Via("resource")
	@Default(values = "Please confirm that you wish to delete the address.")
	public String deleteModalText;
	
	@Inject
	@Via("resource")
	@Default(values = "Cancel")
	public String deleteModalCancelButtonText;
	
	@Inject
	@Via("resource")
	@Default(values = "Confirm")
	public String deleteModalConfirmButtonText;
	
	
	@PostConstruct
	protected void activate() {
		
	}

}
