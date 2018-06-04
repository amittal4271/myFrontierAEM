package com.frontierwholesales.core.beans;

import java.util.Random;

public class FrontierWholesaleOrderItem {

	private FrontierWholesaleOrder order;
	private String itemNum;
	private String description;
	private String skuStatus;
	private String price;
	private String quantity;

	public FrontierWholesaleOrderItem(FrontierWholesaleOrder order) {
		fillWithExampleData(order);
	}
	
	public void fillWithExampleData(FrontierWholesaleOrder order) {
		Random rn = new Random();
		int randomIndex = rn.nextInt(2);

		this.itemNum = exampleItemNum[randomIndex];
		this.description = exampleDescription[randomIndex];
		this.skuStatus = exampleSkuStatus[randomIndex];
		this.price = examplePrice[randomIndex];
		this.quantity = exampleQuantity[randomIndex];
		this.order = order;
	}
	
	private String[] exampleItemNum = {"18034","32525","47744"};
	private String[] exampleDescription = {"Simply Organic Adobo Seasoning 4.41 oz.","Simply Organic Non-Alcoholic Vanilla Flavoring 2 fl. oz.","Frontier Co-op Almond Flavor 16 fl. oz."};
	private String[] exampleSkuStatus = {"In Stock","In Stock","In Stock"};
	private String[] examplePrice = {"$4.48","$6.48","$8.20"};
	private String[] exampleQuantity = {"1","3","2"};
	
		
	public FrontierWholesaleOrder getOrder() {
		return order;
	}

	public String getItemNum() {
		return itemNum;
	}

	public String getDescription() {
		return description;
	}

	public String getSkuStatus() {
		return skuStatus;
	}

	public String getPrice() {
		return price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setOrder(FrontierWholesaleOrder order) {
		this.order = order;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSkuStatus(String skuStatus) {
		this.skuStatus = skuStatus;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
