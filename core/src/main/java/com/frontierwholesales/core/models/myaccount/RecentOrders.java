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
public class RecentOrders {

	private Logger log = LoggerFactory.getLogger(RecentOrders.class);
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
	
	//Recent Orders Row Labels
	@Inject
	@Via("resource")
	@Default(values = "Recent Orders")
	public String recentOrdersListHeader;

	@Inject
	@Via("resource")
	@Default(values = "Web Order")
	public String webOrderColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Date")
	public String dateColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Order")
	public String orderColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Ship")
	public String shipColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "PO Number")
	public String poNumberColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Status")
	public String statusColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Invoice")
	public String invoiceColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Actions")
	public String actionColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Reorder")
	public String reorderButtonText;

	
	// Recent Orders Items Row Labels...
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
	@Default(values = "Past Qty")
	public String pastQtyColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "New Qty")
	public String newQtyColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Actions")
	public String actionsColumnHeader;

	@Inject
	@Via("resource")
	@Default(values = "Add to Cart")
	public String addToCartButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Add to Shelves")
	public String addToShelvesButtonText;
	
	@PostConstruct
	protected void activate() {
		
	}

}
