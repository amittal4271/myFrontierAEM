package com.frontierwholesales.core.services;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import com.frontierwholesales.core.beans.Link;

public interface BrandsService {

	public static final String BRAND_PAGES_START_PATH = "/content/frontierwholesales";
	
	public static final String BRAND_PAGE_RESOURCE_TYPE = "frontierwholesales/components/structure/page";	// TODO need correct resource type for brand page
	
	List<Link> getBrands( ResourceResolver resourceResolver );
}
