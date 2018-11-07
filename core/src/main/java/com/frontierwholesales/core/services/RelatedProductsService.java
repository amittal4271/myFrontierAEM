package com.frontierwholesales.core.services;

import java.util.Collection;

import org.apache.sling.api.SlingHttpServletRequest;

import com.frontierwholesales.core.beans.FrontierWholesalesProducts;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;

public interface RelatedProductsService {
	
	 Collection<FrontierWholesalesProducts> getRelatedProducts( SlingHttpServletRequest request, String sku ) throws FrontierWholesalesBusinessException;
	
}
