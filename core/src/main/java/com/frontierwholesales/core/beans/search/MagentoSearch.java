package com.frontierwholesales.core.beans.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class MagentoSearch {
	public static final String SORT_ORDER_ASC = "ASC";
	public static final String SORT_ORDER_DESC = "DESC";
	
	private int pageSize = 20;
	private int currentPage = 0;
	
	private List<MagentoSearchFilterGroup> filterGroups = new ArrayList<>();
	private List<String> sortList = new ArrayList<>();

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public void addSortBy(@Nonnull String field) {
		int index = sortList.size();
		String query = "searchCriteria[sortOrders][" + index + "][field]=" + field;
		sortList.add(query);
	}
	
	public void addSortBy(@Nonnull String field, @Nonnull String direction) {
		int index = sortList.size();
		String query = "searchCriteria[sortOrders][" + index + "][field]=" + field + "&searchCriteria[sortOrders][" + index + "][direction]=" + direction;
		sortList.add(query);
	}

	public void addFilter(@Nonnull MagentoSearchFilterGroup group) {
		filterGroups.add(group);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int index = 0; index < filterGroups.size(); index++) {
			MagentoSearchFilterGroup group = filterGroups.get(index);
			
			if(index > 0) {
				sb.append('&');
			}
			
			sb.append(group.getQueryString(index));
		}
		
		for(String q : sortList) {
			sb.append('&').append(q);
		}
		sb.append("&searchCriteria[pageSize]=").append(pageSize);
		sb.append("&searchCriteria[currentPage]=").append(currentPage);
		
		return sb.toString();
	}

}