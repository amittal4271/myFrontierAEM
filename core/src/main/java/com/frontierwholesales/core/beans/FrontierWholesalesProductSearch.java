package com.frontierwholesales.core.beans;

public class FrontierWholesalesProductSearch {

	private String sortByNewProduct;
	private String sortByFeatured;
	private String sortByPrice;
	private int currentPage;
	private int categoryId;
	private int noOfRecsPerPage;
	private String facetSearchQuery;
	
	public String getSortByNewProduct() {
		return sortByNewProduct;
	}
	public void setSortByNewProduct(String sortByNewProduct) {
		this.sortByNewProduct = sortByNewProduct;
	}
	public String getSortByFeatured() {
		return sortByFeatured;
	}
	public void setSortByFeatured(String sortByFeatured) {
		this.sortByFeatured = sortByFeatured;
	}
	public String getSortByPrice() {
		return sortByPrice;
	}
	public void setSortByPrice(String sortByPrice) {
		this.sortByPrice = sortByPrice;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getNoOfRecsPerPage() {
		return noOfRecsPerPage;
	}
	public void setNoOfRecsPerPage(int noOfRecsPerPage) {
		this.noOfRecsPerPage = noOfRecsPerPage;
	}
	public String getFacetSearchQuery() {
		return facetSearchQuery;
	}
	public void setFacetSearchQuery(String facetSearchQuery) {
		this.facetSearchQuery = facetSearchQuery;
	}
	
	
	
	
	
}
