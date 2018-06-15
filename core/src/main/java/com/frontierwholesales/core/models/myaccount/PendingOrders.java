package com.frontierwholesales.core.models.myaccount;

import java.util.ArrayList;

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

import com.frontierwholesales.core.beans.FrontierWholesaleUser;


@Model (adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class PendingOrders {

	private Logger log = LoggerFactory.getLogger(PendingOrders.class);

	public ArrayList<FrontierWholesaleUser> users = new ArrayList<FrontierWholesaleUser>();
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
	
	//Pending Orders Row Labels
	@Inject
	@Via("resource")
	@Default(values = "Select")
	public String selectColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Select")
	public String selectCheckboxLabel;

	@Inject
	@Via("resource")
	@Default(values = "Order Name")
	public String orderNameColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Status")
	public String statusColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Lines")
	public String linesColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Subtotal")
	public String subtotalColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Activity")
	public String activityColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Detail")
	public String detailColumnHeader;

	
	// Pending Orders Items Row Labels...
	@Inject
	@Via("resource")
	@Default(values = "Item #")
	public String itemNumColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Description")
	public String descriptionColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "SKU Status")
	public String skuStatusColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Price")
	public String priceColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Qty")
	public String qtyColumnHeader;
	
	@PostConstruct
	protected void activate() {
		
	}

}
