package com.frontierwholesales.core.models.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.frontierwholesales.core.beans.FrontierWholesalesProducts;
import com.frontierwholesales.core.models.RelatedProducts;
import com.frontierwholesales.core.services.RelatedProductsService;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, adapters = RelatedProducts.class, resourceType = RelatedProductsImpl.RESOURCE_TYPE, 
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class RelatedProductsImpl implements RelatedProducts {
	protected static final String RESOURCE_TYPE = "frontierwholesales/components/content/product/related-products";

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private RelatedProductsService productsService;
	
	// Using @ScriptVariable results in resource resolver being NULL for some reason
	@SlingObject
    private ResourceResolver resourceResolver;
	
	@ScriptVariable
	private Page currentPage;
	
	@RequestAttribute 
	@Default(values=" ")
	private String mainProductSku;
	
	@ValueMapValue(name = JcrConstants.JCR_TITLE, injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(values = "Related Products")
    private String heading;
	
	@ValueMapValue(name = "max", injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(intValues = 5)
	private int maxItems;
	
	@ValueMapValue(name = "noRelatedProductsText", injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(values = "No related products available")
	private String defaultText;
	
	private Collection<FrontierWholesalesProducts> relatedProductsList;
	
	@Inject
	public SlingHttpServletRequest slingHttpServletRequest;
	
	@PostConstruct
	protected void initModel() {
		// get sku, current page = product page
		log.debug("Product SKU passed in from request attribute: {}", mainProductSku);
		try {
			
			String[] skuSplit = mainProductSku.split("/");
			
		//String productSku = mainProductSku;	// passed in via request attribute from page
			String productSku = skuSplit[skuSplit.length-1];
		
		log.debug("Product SKU from which to get related products: {}", productSku);
		// call service GET /V1/products/{sku}/links/{type} where type = related, cross_sell or up_sell
		this.relatedProductsList = productsService != null && StringUtils.isNotBlank(productSku) ? productsService.getRelatedProducts(slingHttpServletRequest, productSku) : new ArrayList<>();
		log.debug("Related products list size: {}", this.relatedProductsList != null ? this.relatedProductsList.size() : "LIST IS NULL");
		}catch(Exception anyEx) {
			log.error("Exception in getting related products "+anyEx.getMessage());
		}
	}
	
	@Override
	public Collection<FrontierWholesalesProducts> getRelatedProductsList() {
		return this.relatedProductsList;
	}
	
	@Override
	public String getHeading() {
		return this.heading;
	}
	
	@Override
	public int getMaxItems() {
		return this.maxItems;
	}
	
	@Override
	public String getDefaultText() {
		return this.defaultText;
	}
}
