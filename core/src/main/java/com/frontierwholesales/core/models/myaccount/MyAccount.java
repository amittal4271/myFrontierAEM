package com.frontierwholesales.core.models.myaccount;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.beans.FrontierWholesaleOrder;
import com.frontierwholesales.core.beans.FrontierWholesaleUser;
import com.frontierwholesales.core.beans.FrontierWholesaleUserAddress;

@Model (adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class MyAccount {

	private Logger log = LoggerFactory.getLogger(MyAccount.class);
	
	@SlingObject
	SlingHttpServletResponse response;

	@Inject
	SlingHttpServletRequest request;
		
	public ArrayList<FrontierWholesaleUser> getUsersWithPendingOrders() {
		ArrayList<FrontierWholesaleUser> users = new ArrayList<FrontierWholesaleUser>();
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		return users;
	}
	
	public ArrayList<FrontierWholesaleUser> getPendingInvites() {
		ArrayList<FrontierWholesaleUser> users = new ArrayList<FrontierWholesaleUser>();
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		return users;
	}
	
	public ArrayList<FrontierWholesaleOrder> getRecentOrders() {
		ArrayList<FrontierWholesaleOrder> recentOrders = new ArrayList<FrontierWholesaleOrder>();

		for(int i=0;i<4;i++) {
			FrontierWholesaleOrder order = new FrontierWholesaleOrder();
			recentOrders.add(order);
		}
		
		return recentOrders;
		
	}
	
	public ArrayList<FrontierWholesaleUser> getMyClubMembers() {
		ArrayList<FrontierWholesaleUser> users = new ArrayList<FrontierWholesaleUser>();
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		users.add(new FrontierWholesaleUser());
		return users;
	}
	
	public ArrayList<FrontierWholesaleUserAddress> getAddressBook() {
		ArrayList<FrontierWholesaleUserAddress> addresses = new ArrayList<FrontierWholesaleUserAddress>();
		addresses.add(new FrontierWholesaleUserAddress());
		addresses.add(new FrontierWholesaleUserAddress());
		addresses.add(new FrontierWholesaleUserAddress());
		addresses.add(new FrontierWholesaleUserAddress());
		addresses.add(new FrontierWholesaleUserAddress());
		addresses.add(new FrontierWholesaleUserAddress());
		return addresses;
	}
	
	/**
	 * we may be able to do something like only have two get 'users' call to get pending invites or get current 'members'. Then we can use
	 * those calls to build the ui for all the screens. for example for pending orders we could ask for members and then loop over all of
	 * them and show ones with orders in 'pending' status. If we have a service to get this data directly that would be best though, keeping 
	 * business logic out of this side of the system may be smart.
	 *
	 */
	
	@PostConstruct
	protected void activate() {
		
	}

}
