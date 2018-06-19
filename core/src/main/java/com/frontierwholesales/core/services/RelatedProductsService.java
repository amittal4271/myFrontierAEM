package com.frontierwholesales.core.services;

import java.util.Collection;

import org.apache.sling.api.resource.ResourceResolver;

import com.frontierwholesales.core.beans.FrontierWholesalesProducts;

public interface RelatedProductsService {
	
	public static final String COMMERCE_PRODUCT_START_PATH = "/etc/commerce/products";
	public static final String COMMERCE_PRODUCT_RESOURCE_TYPE = "commerce/components/product";
	public static final String PROPERTY_BASE_SKU = "@baseSku";
	
	public static final String PRODUCT_PAGES_START_PATH = "/content/frontierwholesales";
	public static final String PROPERTY_PRODUCT_MASTER = "jcr:content/@cq:productMaster";
	public static final String PRODUCT_PAGE_RESOURCE_TYPE = "frontierwholesales/components/structure/1-column-page"; 	// TODO
	public static final String PRODUCT_PAGE_OTHER_RESOURCE_TYPE = "frontierwholesales/components/structure/page";	// TODO
	public static final String PRODUCT_COMPONENT_RESOURCE_TYPE = "frontierwholesales/components/content/product/product";	// TODO
	public static final String PROPERTY_PRODUCT_DATA = "jcr:content/root/product/@productData";	
	public static final String PROPERTY_VALUE_PRODUCT = "product";
	
	Collection<FrontierWholesalesProducts> getRelatedProducts( String sku );
	Collection<FrontierWholesalesProducts> getRelatedProducts( ResourceResolver resourceResolver, String sku );
	
	FrontierWholesalesProducts getProductForSku( ResourceResolver resourceResolver, String sku );
}
