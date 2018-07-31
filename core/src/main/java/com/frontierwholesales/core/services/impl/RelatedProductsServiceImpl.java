package com.frontierwholesales.core.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.commerce.api.CommerceConstants;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.foundation.Image;
import com.frontierwholesales.core.beans.FrontierWholesalesProducts;
import com.frontierwholesales.core.magento.models.MagentoRelatedProduct;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
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

	// @Reference does NOT work here! Throws annotation type not applicable to this kind of declaration when compiling!!!
	// and The annotation @Reference is disallowed for this location in eclipse
	// see below for bind/unbind
    private transient ResourceResolverFactory resourceResolverFactory;

	private transient QueryBuilder queryBuilder;
	
	private FrontierWholesalesMagentoCommerceConnector magentoConnector;

	
	@Override
	public Collection<FrontierWholesalesProducts> getRelatedProducts( SlingHttpServletRequest request, String sku ) throws Exception {
		if( magentoConnector == null ) {
			magentoConnector = new FrontierWholesalesMagentoCommerceConnector();
		}
		Collection<FrontierWholesalesProducts> listItems = new ArrayList<>();
		
		String adminToken = null;
		try {
			adminToken = magentoConnector != null ? magentoConnector.getAdminToken() : null;
		} catch( Exception ex ) {
			log.error("Exception trying to retrieve admin token for Magento connector:\n{}", ex);
		}

		if( adminToken != null ) {
			// call service GET /V1/products/{sku}/links/{type} where type = related
			List<MagentoRelatedProduct> productList = magentoConnector != null ? magentoConnector.getRelatedProductsForSku(adminToken, sku) : null;
			if( productList != null && !productList.isEmpty() ) {
				log.debug("Related product list is not empty");
				for( MagentoRelatedProduct magentoProduct : productList ) {
					log.debug("inside the loop ");
					String relatedSku = magentoProduct.getLinkedProductSku();
					log.debug("related sku "+relatedSku);
					
					String productDetails = magentoConnector.getProductDetails(adminToken,relatedSku);
					
					//FrontierWholesalesProducts product = StringUtils.isNotBlank(relatedSku) ? getProductForSku(resourceResolver, relatedSku) : null;
					log.debug("product is "+productDetails);
					// TODO need to get path to product page
					if( productDetails != null ) {
						log.debug("before adding into listitems");
						listItems.add(parseJsonObject(productDetails,request));
						log.debug("after adding into the list");
					}
				}
			}
		}

		return listItems;
	}
	
	
	private FrontierWholesalesProducts parseJsonObject(String productDetails,SlingHttpServletRequest request) throws Exception{
		FrontierWholesalesUtils utils = new FrontierWholesalesUtils();
		Gson json = new Gson();
		JsonElement element = json.fromJson(productDetails, JsonElement.class);
		
		
		JsonObject object = element.getAsJsonObject();
		
		JsonElement skuElement = object.get("sku");
		log.debug("after sku "+skuElement.getAsString());
		JsonElement name = object.get("name");
		log.debug("after name "+name.getAsString() );
		
		FrontierWholesalesProducts products = new FrontierWholesalesProducts();
		products.setTitle(name.getAsString());
		products.setProductSKU(skuElement.getAsString());
		products.setImagePath(utils.getImagePath(skuElement.getAsString(),request));
		log.debug("after the image");
		return products;
		
		
	}
	
	@Override
	public FrontierWholesalesProducts getProductForSku( ResourceResolver resourceResolver, String sku ) {
		FrontierWholesalesProducts product = null;

		if( resourceResolver == null ) {
			resourceResolver = getResourceResolver();
			log.debug("Resource resolver was NULL from calling class, attempting to get service user: {}", resourceResolver);
		}
		
		if( queryBuilder == null ) {
			queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
		}
		Map<String, String> productPredicates = new HashMap<String, String>();
		productPredicates.put("path", RelatedProductsService.COMMERCE_PRODUCT_START_PATH);
		productPredicates.put("type", JcrConstants.NT_UNSTRUCTURED);
		productPredicates.put("1_property", RelatedProductsService.PROPERTY_BASE_SKU);
		productPredicates.put("1_property.value", sku);
		productPredicates.put("2_property", JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY);
		productPredicates.put("2_property.value", RelatedProductsService.COMMERCE_PRODUCT_RESOURCE_TYPE);
		productPredicates.put("3_property", CommerceConstants.PN_COMMERCE_TYPE);
		productPredicates.put("3_property.value", "product");
		
		Session session = resourceResolver.adaptTo(Session.class);
		Query query = queryBuilder.createQuery(PredicateGroup.create(productPredicates), session);

		SearchResult result = query.getResult();
		for( Hit hit : result.getHits() ) {
			try {
				Resource productResource = hit.getResource();
				// need to determine the actual product page path of the related product
				log.debug("Path to product node in /etc: {}", productResource.getPath());
				ValueMap productMap = productResource != null ? ResourceUtil.getValueMap(productResource) : ValueMap.EMPTY;
				Resource imageResource = productResource != null ? productResource.getChild("image") : null;
				ValueMap imageMap = imageResource != null ? ResourceUtil.getValueMap(imageResource) : null;
				
				product = new FrontierWholesalesProducts();
				product.setProductSKU(sku);
				product.setTitle(productMap.get(JcrConstants.JCR_TITLE, String.class));
				product.setImagePath(imageMap.get(Image.PN_REFERENCE, String.class));	// is there a default image if none found
				
				String productPagePath = getRelatedProductPagePath(resourceResolver, productResource.getPath());
				product.setPath(productPagePath);
			} catch( RepositoryException repoEx ) {
				log.error("RepositoryException trying to get resource from search result:\n", repoEx);
			}
		}
		return product;
	}
	
	private String getRelatedProductPagePath( ResourceResolver resourceResolver, String productMasterPath ) {
		String productPagePath = null;
		
		if( queryBuilder == null ) {
			queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
		}
		Map<String, String> productPredicates = new HashMap<String, String>();
		productPredicates.put("path", RelatedProductsService.PRODUCT_PAGES_START_PATH);
		productPredicates.put("type", NameConstants.NT_PAGE);
		productPredicates.put("1_property", RelatedProductsService.PROPERTY_PRODUCT_MASTER);
		productPredicates.put("1_property.value", productMasterPath);
		productPredicates.put("2_property", new StringBuilder(JcrConstants.JCR_CONTENT).append("/@").append(JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY).toString());
		productPredicates.put("2_property.value", RelatedProductsService.PRODUCT_PAGE_RESOURCE_TYPE);
		
		Session session = resourceResolver.adaptTo(Session.class);
		Query query = queryBuilder.createQuery(PredicateGroup.create(productPredicates), session);
		query.setHitsPerPage(1);
		
		SearchResult result = query.getResult();
		for( Hit hit : result.getHits() ) {
			try {
				Resource productResource = hit.getResource();
				productPagePath = productResource.getPath();

			} catch( RepositoryException repoEx ) {
				log.error("RepositoryException trying to get resource from search result:\n", repoEx);
			}
		}
		
		return productPagePath;
	}

	@Reference(service = QueryBuilder.class, cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC, unbind = "unbindQueryBuilder") 
	public void bindQueryBuilder( QueryBuilder queryBuilder ) { 
		this.queryBuilder = queryBuilder; 
	}
	
	public void unbindQueryBuilder( QueryBuilder queryBuilder ) {
		if( this.queryBuilder == queryBuilder ) {
			this.queryBuilder = null;
		}
	}
	
	@Reference(service = ResourceResolverFactory.class, cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC, unbind = "unbindResourceResolverFactory")
	public void bindResourceResolverFactory( ResourceResolverFactory resourceResolverFactory ) {
		this.resourceResolverFactory = resourceResolverFactory;
	}
	
	public void unbindResourceResolverFactory( ResourceResolverFactory resourceResolverFactory ) {
		if( this.resourceResolverFactory == resourceResolverFactory ) {
			this.resourceResolverFactory = null;
		}
	}
	
	@Reference(service = FrontierWholesalesMagentoCommerceConnector.class, cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC, unbind = "unbindFrontierWholesalesMagentoCommerceConnector")
	public void bindFrontierWholesalesMagentoCommerceConnector( FrontierWholesalesMagentoCommerceConnector frontierWholesalesMagentoCommerceConnector ) {
		this.magentoConnector = frontierWholesalesMagentoCommerceConnector;
	}
	
	public void unbindFrontierWholesalesMagentoCommerceConnector( FrontierWholesalesMagentoCommerceConnector frontierWholesalesMagentoCommerceConnector ) {
		if( this.magentoConnector == frontierWholesalesMagentoCommerceConnector ) {
			this.magentoConnector = null;
		}
	}
	
	private ResourceResolver getResourceResolver() {
		Map<String, Object> param = new HashMap<String, Object>();  
		// TODO get correct service user
		param.put(ResourceResolverFactory.SUBSERVICE, "frontierwholesales-service-user");
        ResourceResolver resourceResolver = null;
		try {
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
		} catch( LoginException logEx ) {
			log.error("LoginException trying to get writeService:\n", logEx);
		}
		return resourceResolver;
	}
}
