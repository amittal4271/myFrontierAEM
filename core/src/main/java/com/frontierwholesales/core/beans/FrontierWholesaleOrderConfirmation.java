package com.frontierwholesales.core.beans;

import java.util.List;

public class FrontierWholesaleOrderConfirmation {

	private String confirmationId;
	private String customerEmail;
	private List<Items> items;
	
	
	
	
	public String getConfirmationId() {
		return confirmationId;
	}




	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}




	public List<Items> getItems() {
		return items;
	}




	public void setItems(List<Items> items) {
		this.items = items;
	}

	


	public String getCustomerEmail() {
		return customerEmail;
	}




	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}




	public class Items{
		private  String sku;
		private  String name;
		private  String qty_ordered;
		private  String price;
		
		public String getSku() {
			return sku;
		}
		public void setSku(String sku) {
			this.sku = sku;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getQty_ordered() {
			return qty_ordered;
		}
		public void setQty_ordered(String qty_ordered) {
			this.qty_ordered = qty_ordered;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		
		
		
		
	}
}
