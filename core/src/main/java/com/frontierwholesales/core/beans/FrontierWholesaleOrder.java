package com.frontierwholesales.core.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrontierWholesaleOrder {

	private ArrayList<FrontierWholesaleOrderItem> orderItems = new ArrayList<FrontierWholesaleOrderItem>();
	private String name;
	private String status;
	private String lines;
	private String subtotal;
	private String activity;
	private String userName;

	public FrontierWholesaleOrder(FrontierWholesaleUser user) {
		fillWithExampleData(user);
	}

	public void fillWithExampleData(FrontierWholesaleUser user) {
		Random rn = new Random();
		int randomIndex = rn.nextInt(4);
		
		for(int i=0;i<2+randomIndex;i++) {
			FrontierWholesaleOrderItem item = new FrontierWholesaleOrderItem(this);
			orderItems.add(item);
		}

		this.name = exampleOrderName[randomIndex];
		this.status = exampleStatus[randomIndex];
		this.lines = orderItems.size()+"";
		this.subtotal = exampleSubTotal[randomIndex];
		this.activity = exampleActivity[randomIndex];
		this.userName = user.getFullName();
	}
	
	private String[] exampleOrderName = {"A Very New Order","Justin's Renamed Cart","I Also Like to Live Dangerously Cart", "Dr. Evil's Cart"};
	private String[] exampleStatus = {"Approved","Approved","Approved","Approved"};
	private String[] exampleSubTotal = {"$45.94","$29.94","$45.94","$29.94"};
	private String[] exampleActivity = {"03/25/2018","03/24/2018","03/25/2018","03/24/2018",};
	private String[] exampleUserName = {"JUSTIN PRAHST'S","AUSTIN POWER'S","JUSTIN PRAHST'S","AUSTIN POWER'S"};
	
	public List<FrontierWholesaleOrderItem> getOrderItems() {
		return orderItems;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getLines() {
		return lines;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public String getActivity() {
		return activity;
	}

	public String getUserName() {
		return userName;
	}

	public void setOrderItems(ArrayList<FrontierWholesaleOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
