package com.frontierwholesales.core.beans.search;

import java.util.ArrayList;
import java.util.List;

public class MagentoSearchFilterGroup {

	private List<MagentoSearchCondition> searchConditions = new ArrayList<>();
	
	public void addFilterCondition(MagentoSearchCondition condition) {
		searchConditions.add(condition);
	}
	
	public String getQueryString(int index) {
		StringBuilder sb = new StringBuilder();
		
		for(int filterIndex = 0; filterIndex < searchConditions.size(); filterIndex++) {
			MagentoSearchCondition condition = searchConditions.get(filterIndex);
			
			if(filterIndex > 0) {
				sb.append('&');
			}
			
			sb.append(condition.getQueryString(index, filterIndex));
		}
		
		return sb.toString();
	}
}
