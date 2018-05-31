package com.frontierwholesales.core.beans;

public class FrontierWholesaleUserAddress {

	private String name;
	private String company;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	public FrontierWholesaleUserAddress() {
		fillWithExampleData();
	}

	public void fillWithExampleData() {
		
		int randomIndex = 1;
		
		if(Math.random() < 0.5) {
			randomIndex = 0;
		}
		
		this.name = exampleName[randomIndex];
		this.company = exampleCompany[randomIndex];
		this.street1 = exampleStreet1[randomIndex];
		this.street2 = exampleStreet2[randomIndex];
		this.city = exampleCity[randomIndex];
		this.state = exampleState[randomIndex];
		this.zip = exampleZip[randomIndex];
		this.country = exampleCountry[randomIndex];
		
	}
	
	private String[] exampleName = {"Justin Prahst","Austin Powers"};
	private String[] exampleCompany = {"Test Company","MI6"};
	private String[] exampleStreet1 = {"1485 W 6th Ave","456 Easy Street"};
	private String[] exampleStreet2 = {"Second Line","apt 2"};
	private String[] exampleCity = {"Columbus","Denver"};
	private String[] exampleState = {"OH","CO"};
	private String[] exampleZip = {"43212-2429","80249"};
	private String[] exampleCountry = {"United States of America","United States of America"};

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public String getStreet1() {
		return street1;
	}

	public String getStreet2() {
		return street2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}