package com.frontierwholesales.core.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.QueryBuilder;
import com.frontierwholesales.core.beans.FrontierWholesalesProducts;
import com.frontierwholesales.core.magento.models.MagentoRelatedProduct;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesBusinessException;
import com.frontierwholesales.core.magento.services.exceptions.FrontierWholesalesErrorCode;
import com.frontierwholesales.core.services.RelatedProductsService;
import com.frontierwholesales.core.utils.FrontierWholesalesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Component(service = RelatedProductsService.class, immediate = true,
		property = { "process.label=Zilker Related Products Service Impl",
					Constants.SERVICE_DESCRIPTION + "=This is a service which retrieves the related products from a specified product." })
public class RelatedProductsServiceImpl implements RelatedProductsService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private FrontierWholesalesMagentoCommerceConnector magentoConnector;
	private MagentoCommerceConnectorService config;
	@Reference
	public void activate(MagentoCommerceConnectorService config) {
		
		this.config = config;
	}
	
	@Override
	public Collection<FrontierWholesalesProducts> getRelatedProducts( SlingHttpServletRequest request, String sku ) throws FrontierWholesalesBusinessException {
		if( magentoConnector == null ) {
			magentoConnector = new FrontierWholesalesMagentoCommerceConnector();
		}
		Collection<FrontierWholesalesProducts> listItems = new ArrayList<>();
		
		String adminToken = null;
		try {
			adminToken = this.config.getAppToken();
			String server= this.config.getServer();
			magentoConnector.setServer(server);

		if( adminToken != null ) {
			// call service GET /V1/products/{sku}/links/{type} where type = related
			List<MagentoRelatedProduct> productList = magentoConnector.getRelatedProductsForSku(adminToken, sku);
			if( productList != null && !productList.isEmpty() ) {
				log.debug("Related product list is not empty");
				for( MagentoRelatedProduct magentoProduct : productList ) {
					log.debug("inside the loop ");
					String relatedSku = magentoProduct.getLinkedProductSku();
					log.debug("related sku "+relatedSku);
					
					String productDetails = magentoConnector.getProductDetails(adminToken,relatedSku);
					
					log.debug("product is "+productDetails);
					
					if( productDetails != null ) {
						log.debug("before adding into listitems");
						listItems.add(parseJsonObject(productDetails,request));
						log.debug("after adding into the list");
					}
				}
			}
		}
		} catch( Exception ex ) {
			log.error("Exception in getRelatedProducts:\n{}", ex);
			throw new FrontierWholesalesBusinessException("Service Error ",FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
		}
		return listItems;
	}
	
	
	private FrontierWholesalesProducts parseJsonObject(String productDetails,SlingHttpServletRequest request) throws FrontierWholesalesBusinessException{
		FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
		Gson json = new Gson();
		JsonElement element = json.fromJson(productDetails, JsonElement.class);
		
		
		JsonObject object = element.getAsJsonObject();
		
		JsonElement skuElement = object.get("sku");
		log.debug("after sku "+skuElement.getAsString());
		JsonElement name = object.get("name");
		log.debug("after name "+name.getAsString() );
		
		FrontierWholesalesProducts products = new FrontierWholesalesProducts();
		try {
			products.setTitle(name.getAsString());
			products.setProductSKU(skuElement.getAsString());
			products.setUrlPath(name.getAsString().toLowerCase().replaceAll(" ", "-"));
			products.setImagePath(utils.getImagePath(skuElement.getAsString(),request));
		} catch( Exception ex ) {
			log.error("Exception in parseJsonObject:\n{}", ex);
			throw new FrontierWholesalesBusinessException("Service Error ",FrontierWholesalesErrorCode.GENERAL_SERVICE_ERROR);
		}
		log.debug("after the image");
		return products;
		
		
	}
	
	
}
