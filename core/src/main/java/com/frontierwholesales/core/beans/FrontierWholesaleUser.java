package com.frontierwholesales.core.beans;

import java.util.ArrayList;

public class FrontierWholesaleUser {

	private String firstName;
	private String lastName;
	private String email;
	private String inviteStatus;
	private String userRole;
	
	private ArrayList<FrontierWholesaleOrder> pendingOrders = new ArrayList<FrontierWholesaleOrder>();

	public FrontierWholesaleUser() {
		fillWithExampleData();
	}

	public void fillWithExampleData() {
		
		int randomIndex = 1;
		
		if(Math.random() < 0.5) {
			randomIndex = 0;
		}
		
		this.firstName = exampleFirstName[randomIndex];
		this.lastName = exampleLastName[randomIndex];
		this.email = exampleEmail[randomIndex];
		this.inviteStatus = exampleStatus[randomIndex];
		this.userRole = exampleUserRole[randomIndex];
		
		for(int i=0;i<2+randomIndex;i++) {
			FrontierWholesaleOrder order = new FrontierWholesaleOrder();
			order.setUserName(this.getFullName());
			pendingOrders.add(order);
		}
		
	}
	
	private String[] exampleFirstName = {"JUSTIN","AUSTIN"};
	private String[] exampleLastName = {"PRAHST","POWERS"};
	private String[] exampleEmail = {"jprahst32@gmail.com","austinp007@gmail.com"};
	private String[] exampleStatus = {"Invited","Invited"};
	private String[] exampleUserRole = {"Owner","Owner"};
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getInviteStatus() {
		return inviteStatus;
	}

	public String getUserRole() {
		return userRole;
	}

	public ArrayList<FrontierWholesaleOrder> getPendingOrders() {
		return pendingOrders;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPendingOrders(ArrayList<FrontierWholesaleOrder> pendingOrders) {
		this.pendingOrders = pendingOrders;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setInviteStatus(String inviteStatus) {
		this.inviteStatus = inviteStatus;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}