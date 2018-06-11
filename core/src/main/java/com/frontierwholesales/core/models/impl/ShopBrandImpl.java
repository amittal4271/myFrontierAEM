package com.frontierwholesales.core.models.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.commons.jcr.JcrConstants;
import com.frontierwholesales.core.beans.Link;
import com.frontierwholesales.core.models.ShopBrand;
import com.frontierwholesales.core.services.BrandsService;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, adapters = ShopBrand.class, resourceType = ShopBrandImpl.RESOURCE_TYPE, 
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class ShopBrandImpl implements ShopBrand {
	protected static final String RESOURCE_TYPE = "frontierwholesales/components/content/shop-brand";

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private BrandsService brandsService;
	
	// Using @ScriptVariable results in resource resolver being NULL for some reason
	@SlingObject
	private ResourceResolver resourceResolver;
	
	@ValueMapValue
	@Default(values = "Shop by Brand")
    private String heading;
	
	@ValueMapValue
	private String brandPage1;
	
	@ValueMapValue
	private String brandPage2;
	
	@ValueMapValue
	private String brandPage3;
	
	private Link brandPageLink1;
	
	private Link brandPageLink2;
	
	private Link brandPageLink3;
	
	private Map<String, List<Link>> brandsMap;
	
	@PostConstruct
	protected void initModel() {
		// get image from each brand page
		this.brandPageLink1 = getBrandImage(this.brandPage1);
		this.brandPageLink2 = getBrandImage(this.brandPage2);
		this.brandPageLink3 = getBrandImage(this.brandPage3);
		
		if( this.brandsMap == null ) {
			this.brandsMap = new LinkedHashMap<String, List<Link>>();
		}
		// call service to get the list of brands from Magento and then put in map with first letter as key and list of all brands with that letter
		this.constructBrandsMap();
		log.debug("Shop By Brand map size: {}", this.brandsMap != null ? this.brandsMap.size() : " MAP SIZE IS NULL");
	}
	
	private Link getBrandImage( String brandPagePath ) {
		Resource brandPageResource = StringUtils.isNotBlank(brandPagePath) && resourceResolver != null ? resourceResolver.getResource(brandPagePath) : null;
		// TODO where is the image on the page?
		Resource brandPageContentResource = brandPageResource != null ? brandPageResource.getChild(JcrConstants.JCR_CONTENT) : null;
		ValueMap brandPageContentProps = brandPageContentResource != null ? ResourceUtil.getValueMap(brandPageContentResource) : ValueMap.EMPTY;
		String brandImagePath = brandPageContentProps.get("coverImage", String.class);
		Link brandPageLink = new Link();
		brandPageLink.setIconPath(brandImagePath);
		brandPageLink.setLink(brandPagePath);
		return brandPageLink;
	}
	
	private void constructBrandsMap() {
		// call service GET /rest/V1/products/attributes/manufacturer/options to get list of brands
		List<Link> sortedBrandsList = brandsService != null ? brandsService.getBrands(resourceResolver) : null;
		final Pattern pattern = Pattern.compile("^(\\d||\\W)$");
		String key = "#";
		List<Link> brandsList = new ArrayList<Link>();
		for( Link brandLink : sortedBrandsList ) {
			String firstLetter = brandLink.getTitle().substring(0, 1);
			if( pattern.matcher(firstLetter).find() ) {
				firstLetter = "#";
			}
			
			if( firstLetter.equalsIgnoreCase(key) ) {
				brandsList.add(brandLink);
			} else {
				this.brandsMap.put(key.toUpperCase(), brandsList);
				key = firstLetter;
				brandsList = new ArrayList<Link>();
				// first item in list with new key
				brandsList.add(brandLink);
			}
		}
		if( !brandsList.isEmpty() ) {
			this.brandsMap.put(key.toUpperCase(), brandsList);
		}
	}
	
	@Override
	public String getHeading() {
		return this.heading;
	}
	
	@Override
	public String getBrandPage1() {
		return this.brandPage1;
	}
	
	@Override
	public String getBrandPage2() {
		return this.brandPage2;
	}
	
	@Override
	public String getBrandPage3() {
		return this.brandPage3;
	}
	
	@Override
	public Link getBrandPageLink1() {
		return this.brandPageLink1;
	}
	
	@Override
	public Link getBrandPageLink2() {
		return this.brandPageLink2;
	}
	
	@Override
	public Link getBrandPageLink3() {
		return this.brandPageLink3;
	}
	
	@Override
	public Map<String, List<Link>> getBrandsMap() {
		return this.brandsMap;
	}
}
