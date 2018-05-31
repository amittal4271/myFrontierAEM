package com.frontierwholesales.core.models.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Nonnull;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.List;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.frontierwholesales.core.models.Carousel;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, adapters = {List.class, Carousel.class}, resourceType = CarouselImpl.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CarouselImpl implements Carousel {
	protected static final String RESOURCE_TYPE = "frontierwholesales/components/content/home/carousel";

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Self 
	@Via(value = "core/wcm/components/list/v2/list", type = ResourceSuperType.class)
	private List superTypeList;

	@Self
	private SlingHttpServletRequest request;

	@Override
	@Nonnull
	@JsonProperty("items")
	public Collection<ListItem> getListItems() {
		Collection<ListItem> listItems = new ArrayList<>();
		Collection<Page> pages = getItems(); 
		for( Page page : pages ) {
			if( page != null ) {
				listItems.add(new CarouselListItemImpl(request, page));	// custom
			}
		}
		return listItems;
	}

	@Override
	public Collection<Page> getItems() {
		return superTypeList.getItems();
	}

	@Override
	public boolean linkItems() {
		return superTypeList.linkItems();
	}

	@Override 
	public boolean showDescription() {
		return superTypeList.showDescription();
	}

	@Override
	public boolean showModificationDate() {
		return superTypeList.showModificationDate();
	}

	@Override
	public String getDateFormatString() {
		return superTypeList.getDateFormatString();
	}

	@Override
	public String getExportedType() {
		return superTypeList.getExportedType();
	}
}
