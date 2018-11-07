package com.frontierwholesales.core.magento.services.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.frontierwholesales.core.magento.models.MagentoFacet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class })
public class SearchFacets extends BaseModel {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchFacets.class);

	

	
	private ArrayList<MagentoFacet> facetList = new ArrayList<>();


	@PostConstruct
	protected void init() {
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			connector.setServer(getMagentoServer());
			String searchFacetsListResponse = connector.getProductFacets(getAdminToken());
			
			Gson json = new Gson();
			JsonElement element = json.fromJson(searchFacetsListResponse, JsonElement.class);
			
			JsonObject object = element.getAsJsonObject();
			JsonArray itemArray = object.getAsJsonArray("items");
			
			facetList = mapper.readValue(itemArray.toString(), new TypeReference<List<MagentoFacet>>() {});
			
		} catch (Exception e) {
			LOGGER.error("Error in searchFacets ", e, e.getMessage());
		}
	}

	public List<MagentoFacet> getFacets() {
		return facetList;
	}


}
