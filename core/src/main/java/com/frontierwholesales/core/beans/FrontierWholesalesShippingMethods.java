package com.frontierwholesales.core.beans;

public class FrontierWholesalesShippingMethods {

	private String carrier_code;
	private String method_code;
	private String carrier_title;
	private String method_title;
	private double amount;
	private double base_amount;
	private boolean available;
	private String error_message;
	private double price_excl_tax;
	private double price_incl_tax;
	public String getCarrier_code() {
		return carrier_code;
	}
	public void setCarrier_code(String carrier_code) {
		this.carrier_code = carrier_code;
	}
	public String getMethod_code() {
		return method_code;
	}
	public void setMethod_code(String method_code) {
		this.method_code = method_code;
	}
	public String getCarrier_title() {
		return carrier_title;
	}
	public void setCarrier_title(String carrier_title) {
		this.carrier_title = carrier_title;
	}
	public String getMethod_title() {
		return method_title;
	}
	public void setMethod_title(String method_title) {
		this.method_title = method_title;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBase_amount() {
		return base_amount;
	}
	public void setBase_amount(double base_amount) {
		this.base_amount = base_amount;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public double getPrice_excl_tax() {
		return price_excl_tax;
	}
	public void setPrice_excl_tax(double price_excl_tax) {
		this.price_excl_tax = price_excl_tax;
	}
	public double getPrice_incl_tax() {
		return price_incl_tax;
	}
	public void setPrice_incl_tax(double price_incl_tax) {
		this.price_incl_tax = price_incl_tax;
	}
	
	
}
