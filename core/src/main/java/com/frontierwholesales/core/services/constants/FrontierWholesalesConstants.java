package com.frontierwholesales.core.services.constants;

public final class FrontierWholesalesConstants {
	
	private FrontierWholesalesConstants() {
		
	}

	public static final String MAGENTO_ADMIN_TOKEN="MagentoAdminToken";
	
	public static final String MAGENTO_USER_TOKEN="MagentoUserToken";
	
	public static final String CUSTOMER_ID ="CustomerId";
	
	public static final String MAGENTO_CATEGORIES = "MagentoCategories";
	
	public static final String AEM_CHILDREN_PAGES ="AemChildrenPages";
	
	public static final String AUTHORIZATION="Authorization";
	
	public static final String UTF8="UTF-8";
	
	public static final String CONTENT_TYPE_JSON="application/json";
	
	public static final String CONTENT_TYPE="ContentType";
	
	public static final String ERROR="Error";
	
	public static final String CONSTANT_EXTENSION_HTML = ".html";
	
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
	
	public static final String IMAGE="image";
	
	public static final String BEARER="Bearer ";
}
