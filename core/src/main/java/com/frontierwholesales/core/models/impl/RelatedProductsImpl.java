package com.frontierwholesales.core.models.impl;

import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.commerce.api.CommerceConstants;
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
	protected static final String RESOURCE_TYPE = "frontierwholesales/components/structure/products/related-products";

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private RelatedProductsService productsService;
	
	// Using @ScriptVariable results in resource resolver being NULL for some reason
	@SlingObject
    private ResourceResolver resourceResolver;
	
	@ScriptVariable
	private Page currentPage;
	
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
	
	@PostConstruct
	protected void initModel() {
		// get sku from product page
		ValueMap pageProps = currentPage != null ? currentPage.getProperties() : ValueMap.EMPTY;
		// TODO get correct sku from page
		String productMaster = pageProps.get("cq:productMaster", String.class);
		if( StringUtils.isBlank(productMaster) ) {
			Resource productResource = currentPage != null ? currentPage.getContentResource("root/product") : null;
			ValueMap productProps = productResource != null ? ResourceUtil.getValueMap(productResource) : ValueMap.EMPTY;
			productMaster = productProps.get(CommerceConstants.PN_PRODUCT_DATA, String.class);
		}
		Resource commerceResource = StringUtils.isNotBlank(productMaster) && resourceResolver != null ? resourceResolver.getResource(productMaster) : null;
		ValueMap commerceProps = commerceResource != null ? ResourceUtil.getValueMap(commerceResource) : ValueMap.EMPTY;
		String productSku = commerceProps.get("baseSku", String.class);
		log.debug("Product SKU from which to get related products: {}", productSku);
		// call service GET /V1/products/{sku}/links/{type} where type = related, cross_sell or up_sell
		this.relatedProductsList = productsService != null && StringUtils.isNotBlank(productSku) ? productsService.getRelatedProducts(resourceResolver, productSku) : new ArrayList<FrontierWholesalesProducts>();
		log.debug("Related products list size: {}", this.relatedProductsList != null ? this.relatedProductsList.size() : "LIST IS NULL");
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
