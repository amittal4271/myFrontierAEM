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
	
	
	//Actions section content
	
	@Inject
	@Via("resource")
	@Default(values = "Approval Actions")
	public String approvalActionsSectionHeader;

	@Inject
	@Via("resource")
	@Default(values = "Approve Orders")
	public String approveOrdersButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Mark as Draft")
	public String markAsDraftButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Delete Orders")
	public String deleteOrdersButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Checkout")
	public String checkoutButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Approval Confirmation")
	public String approvalConfirmationModalHeader;

	@Inject
	@Via("resource")
	@Default(values = "<p>You are approving these orders for purchase. This is another explanation for the approval modal.</p>")
	public String approvalConfirmationModalMessage;

	@Inject
	@Via("resource")
	@Default(values = "Yes, Approve Orders")
	public String approvalConfirmationModalConfirmButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Cancel")
	public String approvalConfirmationModalCancelButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Mark as Draft")
	public String markAsDraftModalHeader;

	@Inject
	@Via("resource")
	@Default(values = "<p>You are marking these orders as a draft. These will not be included in checkout cost.</p>")
	public String markAsDraftModalMessage;

	@Inject
	@Via("resource")
	@Default(values = "Mark as Draft")
	public String markAsDraftModalConfirmButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Cancel")
	public String markAsDraftModalCancelButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Delete Confirmation")
	public String deleteOrdersModalHeader;

	@Inject
	@Via("resource")
	@Default(values = "<p>Messaging explaining delete action. This order will be removed from the users cart along with this cart.</p>")
	public String deleteOrdersModalMessage;

	@Inject
	@Via("resource")
	@Default(values = "Yes, Delete Orders")
	public String deleteOrdersModalConfirmButtonText;

	@Inject
	@Via("resource")
	@Default(values = "Cancel")
	public String deleteOrdersModalCancelButtonText;
	
	@PostConstruct
	protected void activate() {
		
	}

}
