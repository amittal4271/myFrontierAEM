package com.frontierwholesales.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.NameConstants;
import com.frontierwholesales.core.beans.Link;
import com.frontierwholesales.core.magento.models.MagentoBrand;
import com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector;
import com.frontierwholesales.core.services.BrandsService;

@Component(service = BrandsService.class, immediate = true,
property = { "process.label=Zilker Brands Service Impl",
			Constants.SERVICE_DESCRIPTION + "=This is a service which retrieves the brands of all products." })
public class BrandsServiceImpl implements BrandsService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// @Reference does NOT work here! Throws annotation type not applicable to this kind of declaration when compiling!!!
	// and The annotation @Reference is disallowed for this location in eclipse
	// see below for bind/unbind
	private transient QueryBuilder queryBuilder;
	
	private FrontierWholesalesMagentoCommerceConnector magentoConnector;
	
	@Override
	public List<Link> getBrands( ResourceResolver resourceResolver ) {
		if( magentoConnector == null ) {
			magentoConnector = new FrontierWholesalesMagentoCommerceConnector();
		}
		List<Link> brandsList = new ArrayList<Link>();
		
		String adminToken = null;
		try {
			adminToken = magentoConnector != null ? magentoConnector.getAdminToken() : null;
		} catch( Exception ex ) {
			log.error("Exception trying to retrieve admin token for Magento connector:\n{}", ex);
		}

		if( adminToken != null ) {
			// call service GET /rest/V1/products/attributes/manufacturer/options
			List<MagentoBrand> magentoBrandList = magentoConnector != null ? magentoConnector.getBrands(adminToken) : new ArrayList<MagentoBrand>();
			
			if( magentoBrandList != null && !magentoBrandList.isEmpty() ) {
				for( MagentoBrand brand : magentoBrandList ) {
					String brandName = brand.getValue();	// TODO is it value or label?
					// TODO find link , brand page
					String brandPagePath = getBrandPagePath(resourceResolver, brandName);
					Link link = new Link();
					link.setTitle(brandName);
					link.setLink(brandPagePath);
					
					brandsList.add(link);
				}
			}

			// sort brands in alphabetical order
			brandsList.sort((Link o1, Link o2)->o1.getTitle().compareToIgnoreCase(o2.getTitle()));
			
		}

		return brandsList;
	}
	
	private String getBrandPagePath( ResourceResolver resourceResolver, String brandName ) {
		String brandPagePath = null;
		
		if( queryBuilder == null && resourceResolver != null ) {
			queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
		}
		Map<String, String> productPredicates = new HashMap<String, String>();
		productPredicates.put("path", BrandsService.BRAND_PAGES_START_PATH);
		productPredicates.put("type", NameConstants.NT_PAGE);
		productPredicates.put("1_property", new StringBuilder(JcrConstants.JCR_CONTENT).append("/@").append(JcrConstants.JCR_TITLE).toString());
		productPredicates.put("1_property.value", brandName);
		// TODO correct query once valid brand page is available
//		productPredicates.put("2_property", new StringBuilder(JcrConstants.JCR_CONTENT).append("/@").append(JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY).toString());
//		productPredicates.put("2_property.value", BrandsService.BRAND_PAGE_RESOURCE_TYPE);
		
		Session session = resourceResolver.adaptTo(Session.class);
		Query query = queryBuilder.createQuery(PredicateGroup.create(productPredicates), session);
		query.setHitsPerPage(1);
		
		SearchResult result = query.getResult();
		for( Hit hit : result.getHits() ) {
			try {
				Resource brandResource = hit.getResource();
				brandPagePath = brandResource.getPath();

			} catch( RepositoryException repoEx ) {
				log.error("RepositoryException trying to get resource from search result:\n", repoEx);
			}
		}
		
		return brandPagePath;
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
}
