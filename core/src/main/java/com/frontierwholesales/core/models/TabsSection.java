package com.frontierwholesales.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;


import com.google.gson.Gson;

@Model(adaptables = SlingHttpServletRequest.class)
public class TabsSection extends BaseComponent {
	

	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] tabItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] southtabItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] northtabItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] residentialtabItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String[] commercialtabItems;
	@Inject
	@Via("resource")
	@Default(values = "")
	private String title;
    private String accordingId;

	
    private List<TabItem> listItems;
    private List<TabItem> southListItems;
    private List<TabItem> northListItems;
    private List<TabItem> commercialListItems;
    private List<TabItem> residentailListItems;

    
    @Override public void init() {
    	LOG.info("TabsSection Component Model init Begin");
        try {
			listItems = new ArrayList<TabItem>();
			southListItems = new ArrayList<TabItem>();
			northListItems = new ArrayList<TabItem>();
			commercialListItems = new ArrayList<TabItem>();
			residentailListItems = new ArrayList<TabItem>();
			
			LOG.info("Created list Items");
			
			if (isNotEmptyTabItems(tabItems)) {
				buildAccordionItems(listItems, tabItems);
			}
			if (isNotEmptyTabItems(southtabItems)) {
				buildAccordionItems(southListItems, southtabItems);
			}
			if (isNotEmptyTabItems(northtabItems)) {
				buildAccordionItems(northListItems, northtabItems);
			}
			if (isNotEmptyTabItems(commercialtabItems)) {
				buildAccordionItems(commercialListItems, commercialtabItems);
			}
			if (isNotEmptyTabItems(residentialtabItems)) {
				buildAccordionItems(residentailListItems, residentialtabItems);
			}

        } catch (Exception e) {
			LOG.info("TabItem parsys exception: " + e.getMessage());
		}
        LOG.info("TabsSection Component Model init End");
    }

	private boolean isNotEmptyTabItems(String[] pTabItems) {
		return pTabItems !=null && !pTabItems[0].equals("") && ArrayUtils.isNotEmpty(pTabItems);
	}

	private void buildAccordionItems(List<TabItem> pListItems, String[] pTabItems) {
		for(int i=0; i<pTabItems.length; i++){
			String tabItem = pTabItems[i];
			Gson gson = new Gson();	            
			TabItem item = gson.fromJson(tabItem, TabItem.class);
            LOG.info("Tab: " + item.getSectionTitle());
			pListItems.add(item);
			LOG.info(item.getSectionTitle() + " has been selected: "+ item.getDefaultSelection() + " and is: " + item.getSelected());  	
            LOG.info("Accordion's Anchor title: " + item.getAnchorTitle());
		}
	}

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TabItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<TabItem> listItems) {
        this.listItems = listItems;
    }

	public String getAccordingId() {
		return accordingId;
	}

	public void setAccordingId(String accordingId) {
		this.accordingId = accordingId;
	}

	public List<TabItem> getSouthListItems() {
		return southListItems;
	}

	public void setSouthListItems(List<TabItem> southListItems) {
		this.southListItems = southListItems;
	}

	public List<TabItem> getNorthListItems() {
		return northListItems;
	}

	public void setNorthListItems(List<TabItem> northListItems) {
		this.northListItems = northListItems;
	}

	public List<TabItem> getCommercialListItems() {
		return commercialListItems;
	}

	public void setCommercialListItems(List<TabItem> commercialListItems) {
		this.commercialListItems = commercialListItems;
	}

	public List<TabItem> getResidentailListItems() {
		return residentailListItems;
	}

	public void setResidentailListItems(List<TabItem> residentailListItems) {
		this.residentailListItems = residentailListItems;
	}
	
	

}
