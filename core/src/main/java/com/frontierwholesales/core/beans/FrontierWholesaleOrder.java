package com.frontierwholesales.core.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrontierWholesaleOrder {

	private ArrayList<FrontierWholesaleOrderItem> orderItems = new ArrayList<FrontierWholesaleOrderItem>();
	//used in pending orders
	private String name;
	private String lines;
	private String subtotal;
	private String activity;
	private String userName;

	//used in recent orders
	private String webOrderNum;
	private String orderDate;
	private String orderNum;
	private String shipType;
	private String poNumber;
	private String invoiceNum;
	
	//used in both recent and pending orders
	private String status;
	
	public FrontierWholesaleOrder() {
		fillWithExampleData();
	}

	public void fillWithExampleData() {
		Random rn = new Random();
		int randomIndex = rn.nextInt(4);
		
		for(int i=0;i<2+randomIndex;i++) {
			FrontierWholesaleOrderItem item = new FrontierWholesaleOrderItem();
			item.setOrder(this);
			orderItems.add(item);
		}

		this.name = exampleOrderName[randomIndex];
		this.status = exampleStatus[randomIndex];
		this.lines = orderItems.size()+"";
		this.subtotal = exampleSubTotal[randomIndex];
		this.activity = exampleActivity[randomIndex];
		
		this.webOrderNum = exampleWebOrderNum[randomIndex];
		this.orderDate = exampleOrderDate[randomIndex];
		this.orderNum = exampleOrderNum[randomIndex];
		this.shipType = exampleShipType[randomIndex];
		this.poNumber = examplePoNumber[randomIndex];
		this.invoiceNum = exampleInvoiceNum[randomIndex];
		
	}
	
	private String[] exampleOrderName = {"A Very New Order","Justin's Renamed Cart","I Also Like to Live Dangerously Cart", "Dr. Evil's Cart"};
	private String[] exampleStatus = {"Approved","Approved","Approved","Approved"};
	private String[] exampleSubTotal = {"$45.94","$29.94","$45.94","$29.94"};
	private String[] exampleActivity = {"03/25/2018","03/24/2018","03/25/2018","03/24/2018"};
	
	private String[] exampleWebOrderNum = {"648020","648020","648020","648020"};
	private String[] exampleOrderDate = {"05/16/2018","05/16/2018","05/16/2018","05/16/2018"};
	private String[] exampleOrderNum = {"835396","835396","835396","835396"};
	private String[] exampleShipType = {"FedEx","FedEx","FedEx","FedEx"};
	private String[] examplePoNumber = {"51518","51518","51518","51518"};
	private String[] exampleInvoiceNum = {"2653430","2653430","2653430","2653430"};
	
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

	public String getWebOrderNum() {
		return webOrderNum;
	}

	public void setWebOrderNum(String webOrderNum) {
		this.webOrderNum = webOrderNum;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

}
